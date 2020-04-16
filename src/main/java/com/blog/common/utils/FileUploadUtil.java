package com.blog.common.utils;

import ch.qos.logback.core.util.FileSize;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FileUploadUtil {
    private MultipartHttpServletRequest multipartHttpServletRequest;

    /**
     * 上传文件key
     */
    private String fileName;

    /**
     * 保存路径
     */
    private String savePath;
    /**
     * 限制上传文件的大小
     */

    private long maxSize = 0;
    /**
     * 文件名后缀
     */
    private String[] fileExt;
    /**
     * 文件名前缀（区分类别）
     */
    private String prefix;


    public FileUploadUtil(MultipartHttpServletRequest multipartHttpServletRequest) {
        Objects.requireNonNull(multipartHttpServletRequest);
        this.multipartHttpServletRequest = multipartHttpServletRequest;
    }
    /**
     * 设置上传文件key
     *
     * @param fileName 字符串
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * 文件上传大小限制
     *
     * @param maxSize 字符串
     */
    public void setMaxSize(String maxSize) {
        this.maxSize = FileSize.valueOf(maxSize).getSize();
    }

    /**
     * 文件保存的路径
     *
     * @param savePath 路径
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * 限制文件后缀名
     *
     * @param prefix 后缀
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 限制文件后缀名
     *
     * @param fileExt 后缀
     * @return this
     */
    public FileUploadUtil setFileExt(String... fileExt) {
        this.fileExt = fileExt;
        return this;
    }

    public List<String> save(){
        List<MultipartFile> files = multipartHttpServletRequest.getFiles(fileName);
        List<String> url = new ArrayList<>();
        for (MultipartFile file : files) {
            String curFileName = file.getOriginalFilename();
            assert curFileName != null;
            String suffix = curFileName.substring(curFileName.lastIndexOf("."));
            //定义保存路径，如果文件夹不存在则创建

            String filePath = System.getProperty("user.dir") + "/src/main/resources"+this.savePath;

            // 注意路径为全路径
            File checkFilePath = new File(filePath);
            if (!checkFilePath.exists()) {
                checkFilePath.mkdirs();
            }

            String fileName = this.prefix+"_"+new Date().getTime() + suffix;
            try {
                //保存动作
                File dest = new File(filePath + fileName);
                file.transferTo(dest);
                url.add(this.savePath + fileName);
            } catch (IOException e) {
                System.out.println("保存失败");
            }
        }
        return url;
    }
}
