package com.cloud.galaxy.business.common.controller;

import com.cloud.galaxy.business.common.dao.FileMongoRepository;
import com.cloud.galaxy.business.common.entity.po.FilePo;
import com.cloud.galaxy.business.common.entity.vo.FileVo;
import com.cloud.galaxy.business.common.util.FastdfsClientUtil;
import com.cloud.galaxy.common.core.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;
import java.util.function.Function;

@Api(tags = "文件上传下载")
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    FastdfsClientUtil fastdfsClientUtil;
    @Autowired
    FileMongoRepository mongoRepository;
    @Value("${fdfs.tracker-list}")
    private String[] contextPaths;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @ApiOperation("上传文件")
    @PostMapping(value = "uploadFile")
    public R<FileVo> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FilePo filePo = upload(file, (path) -> {
                try {
                    return fastdfsClientUtil.uploadFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
            FileVo fileVo=new FileVo();
            BeanUtils.copyProperties(filePo,fileVo);
            fileVo.setContextPath(getFilePath().getData());
            return R.ok(fileVo);
        } catch (Exception e) {
            return R.fail();
        }
    }

    /**
     * 上传图片并生成缩略图
     *
     * @param file
     * @return
     */
    @ApiOperation("上传图片并生成缩略图")
    @PostMapping(value = "uploadImage")
    public R<FileVo> uploadImage(@RequestParam("file") MultipartFile file,int addWM,String checkImgPath) {
        try {
            FilePo filePo = upload(file, (path) -> {
                try {
                    return fastdfsClientUtil.uploadImageAndCrtThumbImage(file,addWM,checkImgPath);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
            FileVo fileVo=new FileVo();
            BeanUtils.copyProperties(filePo,fileVo);
            fileVo.setContextPath(getFilePath().getData());
            return R.ok(fileVo);
        } catch (Exception e) {
            return R.fail();
        }

    }

    /**
     * 文件上传前后，进行相关的数据库操作。 文件上传操作使用lamada代替，这样后期可以随意切换文件存储工具
     *
     * @param file
     * @param function
     * @return
     * @throws Exception
     */
    private  FilePo upload(MultipartFile file, Function<MultipartFile, String> function) throws IOException {
        String content = DigestUtils.md5Hex(file.getBytes());
        //如果图片已经上传过了，就把已经上传的文件地址返回给用户
        FilePo filePo = mongoRepository.findByContent(content);
        if (filePo != null) {
            return filePo;
        }
        String path = function.apply(file);
        if (path != null && !StringUtils.isEmpty(path)) {
            //上传成功后，把图片的原始文件名和fastdfs存储路径返回给用户。如果用户重复上传文件，就把已经上传过的文件返回给他
            filePo.setFileName(file.getOriginalFilename());
            filePo.setFilePath(path);
            filePo.setContent(content);
            filePo.setSize(file.getSize());
            filePo.setStatus(1);
            mongoRepository.save(filePo);
            return filePo;
        }
        throw new RuntimeException();
    }
    @ApiOperation("获取图片地址")
    @GetMapping("getFilePath")
    public R<String> getFilePath() {
        if (contextPaths.length == 0) {
            return R.fail();
        }
        return R.ok("http://" + contextPaths[new Random().nextInt(contextPaths.length)].split(":")[0] + "/");
    }

    /**
     * 文件删除
     */
    @ApiOperation("文件删除")
    @GetMapping(value = "/deleteByPath")
    public R deleteByPath(@RequestParam String fileUrl) {
        fastdfsClientUtil.deleteFile(fileUrl);
        return R.ok();
    }

    /**
     * 文件下载
     *
     * @param urlString 图片的完整url地址，http://fastdfsIP+port+path
     * @param response
     */
    @ApiOperation("文件下载")
    @GetMapping("download")
    public void download(String urlString, HttpServletResponse response) {

        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            String filename = urlString.split("/")[urlString.split("/").length - 1];
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            is = con.getInputStream();
            bis = new BufferedInputStream(is);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

