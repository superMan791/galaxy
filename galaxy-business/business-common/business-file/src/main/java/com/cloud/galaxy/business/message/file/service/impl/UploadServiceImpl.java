package com.cloud.galaxy.business.message.file.service.impl;

import com.cloud.galaxy.business.message.file.dao.FileMongoRepository;
import com.cloud.galaxy.business.message.file.entity.vo.FileDetailVo;
import com.cloud.galaxy.business.message.file.service.IUploadService;
import com.cloud.galaxy.common.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.InputStream;

@Service
public class UploadServiceImpl extends BaseServiceImpl implements IUploadService {
    @Autowired
    FileMongoRepository fileRepository;

    @Override
    public FileDetailVo uploadFile(InputStream inputStream, String fileName) {
        return null;
    }

    @Override
    public FileDetailVo uploadImage(InputStream inputStream) {
        return null;
    }

    @Override
    public FileDetailVo uploadImage(InputStream inputStream, String fileName, int addWM, String checkImgPath) {
        return null;
    }

    @Override
    public String getFileServerPath() {
        return null;
    }

    @Override
    public void deleteByPath(String filePath) {

    }

    @Override
    public FileDetailVo uploadImage(BufferedImage bufferedImage) {
        return null;
    }

    @Override
    public FileDetailVo uploadImage(BufferedImage bufferedImage, String fileName, int addWM, String checkImgPath) {
        return null;
    }
}
