package com.cloud.galaxy.business.message.file.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Log4j2
public class HdfsUploadUtil {
    public String uploadFile(InputStream inputStream) {
        return null;
    }

    public String uploadImage(InputStream inputStream) {
        return null;
    }

    public Boolean delete(String filePath) {
        return false;
    }

    public String getFileServerPath() {
        return null;
    }
}
