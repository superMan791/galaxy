package com.cloud.galaxy.business.message.file.controller;

import com.cloud.galaxy.business.message.file.entity.vo.FileDetailVo;
import com.cloud.galaxy.business.message.file.service.IUploadService;
import com.cloud.galaxy.common.core.base.R;
import com.google.zxing.WriterException;
import com.kuisama.zxing.ZxingHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api(tags = "文件管理")
public class FileController {

    @Autowired
    private ZxingHandler zxingHandler;
    @Autowired
    private IUploadService uploadService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @ApiOperation("上传文件")
    @PostMapping(value = "uploadFile")
    public R<FileDetailVo> uploadFile(@RequestParam("file") MultipartFile file) {
        return R.ok();
    }

    /**
     * 上传图片,可以生成水印
     *
     * @param file
     * @return
     */
    @ApiOperation("上传图片并生成缩略图")
    @PostMapping(value = "uploadImage")
    public R<FileDetailVo> uploadImage(@RequestParam("file") MultipartFile file, int addWM, String checkImgPath) {
        return R.ok();
    }

    @ApiOperation("获取图片地址")
    @GetMapping("getFileServerPath")
    public R<String> getFilePath() {
        return R.ok();
    }

    /**
     * 文件删除
     */
    @ApiOperation("文件删除")
    @GetMapping(value = "/delete")
    public R deleteByPath(@RequestParam String filePath) {
        return R.ok();
    }

    /**
     * 文件下载
     *
     * @param response
     */
    @ApiOperation("文件下载")
    @GetMapping("download")
    public void download(@RequestParam("filePath") String filePath, HttpServletResponse response) {
    }

    @ApiOperation("生成二维码")
    @GetMapping("createQrCode")
    public R<FileDetailVo> createQrCode(@RequestParam("content") String content) throws WriterException, IOException {
        BufferedImage bufferedImage = zxingHandler.toBufferedImage(content);
        FileDetailVo fileDetailVo = uploadService.uploadImage(bufferedImage);

        if (fileDetailVo == null) {
            return R.fail();
        }
        return R.ok(fileDetailVo);
    }
}
