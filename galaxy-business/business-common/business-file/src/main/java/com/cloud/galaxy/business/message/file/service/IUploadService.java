package com.cloud.galaxy.business.message.file.service;

import com.cloud.galaxy.business.message.file.entity.vo.FileDetailVo;
import com.cloud.galaxy.common.core.base.IBaseService;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface IUploadService extends IBaseService {

    FileDetailVo uploadFile(InputStream inputStream, String fileName);

    FileDetailVo uploadImage(InputStream inputStream);

    FileDetailVo uploadImage(BufferedImage bufferedImage);

    FileDetailVo uploadImage(InputStream inputStream, String fileName, int addWM, String checkImgPath);

    FileDetailVo uploadImage(BufferedImage bufferedImage, String fileName, int addWM, String checkImgPath);

    String getFileServerPath();

    void deleteByPath(String filePath);
}
