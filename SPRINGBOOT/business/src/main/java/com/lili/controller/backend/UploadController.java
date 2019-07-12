package com.lili.controller.backend;

import com.lili.common.ResponseCode;
import com.lili.common.ServerResponse;
import com.lili.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/manage/")
public class UploadController {

    @Value("${business.imageHost}")
    private String imageHost;
    @GetMapping(value = "/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public ServerResponse upload(@RequestParam("upload_file") MultipartFile upload_file) {
        if (upload_file == null || "".equals(upload_file.getOriginalFilename())) {
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "图片必须上传");
        }
        //获取上传图片的名称
        String oldFileName = upload_file.getOriginalFilename();
        //获取文件扩展名
        String extendName = oldFileName.substring(oldFileName.lastIndexOf('.'));
        //生成文件名
        String newFileName = UUID.randomUUID().toString() + extendName;
        File mkdir = new File("f:/upload");
        if (!mkdir.exists()) {
            mkdir.mkdirs();
        }
        File newFile = new File(mkdir, newFileName);
        try {
            upload_file.transferTo(newFile);
            ImageVO imageVO = new ImageVO(newFileName, imageHost + newFileName);
            return ServerResponse.serverResponseBySuccess(imageVO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.serverResponseByError();
    }

}
