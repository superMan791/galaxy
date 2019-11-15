package com.cloud.galaxy.business.common.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

@Component
@Log4j2
public class FastdfsClientUtil {
    @Autowired
    private FastFileStorageClient storageClient;


    //上传文件
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        //文件名
        String originalFilename = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        // 文件扩展名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());


        //生成缩略图
        StorePath storePath = this.storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), originalFilename, null);

        String path = storePath.getFullPath();

        return path;
    }

    //上传文件
    public String uploadImageAndCrtThumbImage(MultipartFile multipartFile, Integer addWM, String checkImagePath, Integer width, Integer height) throws Exception {

        //文件名
        String originalFilename = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        // 文件扩展名
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        StorePath storePath = null;
        if (addWM == 1) {
            URL checkUrl = new URL(checkImagePath);
            BufferedImage checkImage = ImageIO.read(checkUrl);
            BufferedImage sourceImage = ImageIO.read(multipartFile.getInputStream());
            log.info(sourceImage.getWidth());
            log.info(sourceImage.getHeight());
            width = width == null ? sourceImage.getWidth() : width;
            height = height == null ? sourceImage.getHeight() : height;
            BufferedImage sourceImageNew = Thumbnails.of(sourceImage).size(width, height)
                    .watermark(Positions.CENTER_RIGHT, checkImage, 0.5f).asBufferedImage();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
            ImageIO.write(sourceImageNew, ext, imageOutput);
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            storePath = this.storageClient.uploadImageAndCrtThumbImage(inputStream, byteArrayOutputStream.toByteArray().length, originalFilename, null);
        } else {
            storePath = this.storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), originalFilename, null);
        }
        String path = storePath.getFullPath();

        return path;
    }

    /**
     * 删除文件
     *
     * @Param fileUrl 文件访问地址
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage());
        }
    }

}
