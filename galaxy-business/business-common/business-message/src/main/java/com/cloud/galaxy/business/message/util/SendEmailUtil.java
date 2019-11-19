package com.cloud.galaxy.business.message.util;

import com.cloud.galaxy.business.message.entity.EmailMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Log4j2
@Component
public class SendEmailUtil {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送html格式的邮件
     *
     * @throws MessagingException
     */
    public void sendMimeMessage(EmailMessage emailMessage) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailMessage.getTo());

        mimeMessageHelper.setText(emailMessage.getContent(), true);
        mimeMessageHelper.setSubject(emailMessage.getTitle());
        mimeMessageHelper.setFrom(from);

        Map<String, String> inline = emailMessage.getInline();
        if (inline != null && !inline.isEmpty()) {
            for (String key : inline.keySet()) {
                //这里是使用的简单的文件读取，后期整合fastDFS，这一段代码需要重写
                UrlResource resource = getResource(inline.get(key));
                if (resource != null) {
                    mimeMessageHelper.addInline(key, resource);
                }

            }
        }
        Map<String, String> attachment = emailMessage.getAttachment();
        if (attachment != null && !attachment.isEmpty()) {
            for (String key : attachment.keySet()) {
                //这里是使用的简单的文件读取，后期整合fastDFS，这一段代码需要重写
                UrlResource resource = getResource(attachment.get(key));
                if (resource != null) {
                    mimeMessageHelper.addAttachment(key, resource);
                }
            }
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送普通文本邮件
     */
    public void sendSimpleMail(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailMessage.getTitle());
        message.setText(emailMessage.getContent());
        message.setTo(emailMessage.getTo());
        message.setFrom(from);
        mailSender.send(message);
    }

    /**
     * 通过附件的url地址，获取Resource对象，用于图片和附件的发送
     *
     * @param urlString
     * @return
     */
    private UrlResource getResource(String urlString) {
        try {
            UrlResource resource = new UrlResource(new URL(urlString));
            return resource;
        } catch (MalformedURLException e) {
            log.info("url地址为空，抛弃该附件：" + urlString);
            e.printStackTrace();
        }
        return null;
    }
}
