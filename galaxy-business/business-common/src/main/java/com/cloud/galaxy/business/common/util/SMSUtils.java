package com.cloud.galaxy.business.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Component
public class SMSUtils {
    @Value("${sms.urls}")
    private String[] urls;

    @Value("${sms.international.url}")
    private String[] urlsInternational;
    @Value("${my.test")
    private String test;

    /*
     * 发送方法
     */
    public boolean sendSMSContent(String nationCode, String mobile, String content) {
//        String send_content;
        //发送内容

        boolean inputLine = false;
        try {
            if (nationCode.equals("86")) {
                return sendSMSPost(mobile, content, 0);
            } else {
                return sendSMSPostInternational(nationCode + mobile, content, 0);
            }
        } catch (Exception e) {
            log.error("短信：" + mobile + " 发送失败！", e);
        }
        return inputLine;
    }


    /**
     * 国内短信
     *
     * @param mobile
     * @param content
     * @return
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     */
    private boolean sendSMSPost(String mobile, String content, Integer i) throws MalformedURLException,
            UnsupportedEncodingException, NoSuchAlgorithmException {

        //通过迭代下标i,轮询所有短信平台的url地址
        String url = urls[i];
        if (StringUtils.isBlank(url)) {
            return false;
        }
        //通过截取字符串的方式，从https://用户名:密码@域名  的格式中提取域名部分和用户名，密码部分
        String[] corpIdAndPwd = url.substring(url.indexOf("//") + 2).split("@")[0].split(":");
        String corpId = corpIdAndPwd[0];
        String pwd = corpIdAndPwd[1];
        String serverUrl = url.substring(0, url.indexOf("//") + 2) + url.split("@")[1];


        String send_content = URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");// 发送内容
        String password = pwd;//DigestUtils.md5Hex(pwd);
        //password = DigestUtils.md5Hex(password);
        String param = "CorpID=" + corpId + "&Pwd=" + password + "&Mobile=" + mobile
                + "&Content=" + send_content;

        int code = -100;
        try {
            String result = sendPost(serverUrl, param, null);
            log.info("开始发送短信手机号码为:" + mobile + "\r\n结果:" + result);
            if (Pattern.matches("^\\d+$", result)) {
                code = Integer.valueOf(result);
            }

        } catch (Exception e) {
            //通过迭代的方式轮询访问所有短信平台接口
            if (i < urls.length - 1) {
                sendSMSPost(mobile, content, i);
            } else {
                log.error("短信：" + mobile + " 发送失败！", e);
            }
        }
        return code > 0;
    }

    /**
     * 国际短信
     *
     * @param mobile
     * @param content
     * @return
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     */
    private boolean sendSMSPostInternational(String mobile, String content, Integer i) throws MalformedURLException,
            UnsupportedEncodingException {

        //通过迭代下标i,轮询所有短信平台的url地址
        String url = urlsInternational[i];
        if (StringUtils.isBlank(url)) {
            return false;
        }
        //通过截取字符串的方式，从https://用户名:密码@域名  的格式中提取域名部分和用户名，密码部分
        String[] corpIdAndPwd = url.substring(url.indexOf("//") + 2).split("@")[0].split(":");
        String corpIdInternational = corpIdAndPwd[0];
        String pwdInternational = corpIdAndPwd[1];
        String serverUrlInternational = url.substring(0, url.indexOf("//") + 2) + url.split("@")[1];


        String send_content = URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");// 发送内容
        String password = DigestUtils.md5Hex(pwdInternational);
        String param = "CorpID=" + corpIdInternational + "&Pwd=" + password + "&Mobile=" + mobile
                + "&Content=" + send_content;

        int code = -100;
        try {
            String result = sendPost(serverUrlInternational, param, null);
            log.info("开始发送短信手机号码为:" + mobile + "\r\n结果:" + result);
            if (Pattern.matches("^\\d+$", result)) {
                code = Integer.valueOf(result);
            }
        } catch (Exception e) {
            //通过迭代的方式轮询访问所有短信平台接口
            if (i < urlsInternational.length - 1) {
                sendSMSPostInternational(mobile, content, i);
            } else {
                log.error("短信：" + mobile + " 发送失败！", e);
            }
        }

        return code > 0;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private String sendPost(String url, String param, Map<String, String> headers) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (headers != null) {
                for (String k : headers.keySet()) {
                    conn.setRequestProperty(k, headers.get(k));
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
