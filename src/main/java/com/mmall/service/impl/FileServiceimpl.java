package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceimpl implements IFileService {
    private Logger logger= LoggerFactory.getLogger(FileServiceimpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) {
        String fileName=file.getOriginalFilename();
        //获取文件后缀名
        String fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName= UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传的文件名为{}，上传的路径为{}，新文件名为{}",fileName,path,uploadFileName);
        File fileDir=new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile=new File(path,uploadFileName);
        try{
            file.transferTo(targetFile);
            //文件上传成功
            //将targetFile上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //将upload里的文件删除
            targetFile.delete();

        }catch (IOException e){
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
