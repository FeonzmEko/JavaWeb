package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    // 本地磁盘存储方案
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数：{},{},{}",name,age,file);

        // 获取原始文件名
        String originalFileName = file.getOriginalFilename();

        // 生成唯一文件名
        String newFileName = UUID.randomUUID().toString() + originalFileName;

        // 保存文件
        file.transferTo(new File("D:\\develop\\images\\" + newFileName));

        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        // 将文件交给oss来管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());

        return Result.success(url);
    }
}