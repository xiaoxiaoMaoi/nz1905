package com.qf.back.v3.consumer.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.bean.MultiResultBean;
import com.qf.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/20
 */
@Controller
@RequestMapping("file")
public class FileController {

    @Value("${image.server}")
    private String IMAGE_SERVER;

    @Autowired
    private FastFileStorageClient client;

    @RequestMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file){
        //得到文件名称
        String originalFilename = file.getOriginalFilename();
        //截取结尾的名称
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        try {
            //将文件保存到fastDFSF中
            StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), suffix, null);
            //得到文件的存储路径
            String fullPath = storePath.getFullPath();
            //把路径返回给到前端
            String path=new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
            return ResultBean.success(path);

        } catch (IOException e) {
            e.printStackTrace();
            return  ResultBean.erro("操作失败，请稍后再试！");
        }
    }

    @RequestMapping("multiUpload")
    @ResponseBody
    public MultiResultBean multiUpload(MultipartFile[] files){
        MultiResultBean multiResultBean=new MultiResultBean();
        //创建数组用来装文件的地址
        String[] data=new String[files.length];
        for (int i = 0; i < files.length; i++) {
            //的到文件的名称
            String originalFilename = files[i].getOriginalFilename();
            //截取结尾的名称
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //将文件保存到fastFDSF中
            try {
                StorePath storePath = client.uploadFile(files[i].getInputStream(), files[i].getSize(), suffix, null);
                //得到文件的存储路径
                String fullPath = storePath.getFullPath();
                //把路径返回给到前端
                String path=new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
                data[i]=path;
            } catch (IOException e) {
                e.printStackTrace();
                //失败时的提示
                multiResultBean.setErrno("-1");
                return multiResultBean;
            }
        }
        //成功之后提示
        multiResultBean.setErrno("0");
        multiResultBean.setData(data);
        return multiResultBean;
    }
}
