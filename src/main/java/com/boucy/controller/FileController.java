package com.boucy.controller;

import com.boucy.service.BookService;
import com.boucy.service.UserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    //    头像文件存储位置
    private final static String HEADFILESSERVER = "http://127.0.0.1:8090/uploadHeadPhoto/";
    //    图书封面存储位置
    private final static String BOOKPHOTOFILESERVER = "http://127.0.0.1:8090/uploadBookPhoto/";
    //    图书存储位置
    private final static String BOOKFILESERVER = "http://127.0.0.1:8090/uploadBook/";

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/headPhotoUpload")
    @ResponseBody
    public Map<String, String> uploadHeadPhoto(MultipartFile headPhoto, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<>();
//        控制文件大小
//        if (headPhoto.getSize() > 1024 * 1024 * 5) {
//            map.put("message", "文件大小不能超过5MB");
//            return map;
//        }

//        获取文件名
        String originalFilename = headPhoto.getOriginalFilename();
//        避免文件冲突,使用UUID替换文件名
        String uuid = UUID.randomUUID().toString();
//        获取拓展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));

//        控制文件上传类型
//        if(!extendName.equals(".pdf")){
//            map.put("message","文件类型必须是.pdf");
//            return map;
//        }

//        新的文件名
        String newFileName = uuid.concat(extendName);

//        创建sun公司提供的jersey包下的client对象
        Client client = Client.create();
        WebResource resource = client.resource(HEADFILESSERVER + newFileName);
//        文件保存到另一个服务器上去了
        resource.put(String.class, headPhoto.getBytes());

//        上传成功之后，把文件的名字和文件的类型返回给浏览器
        map.put("message","上传成功");
//        附加文件名字
        map.put("newFileName",newFileName);
//        附加文件类型
        map.put("fileType",headPhoto.getContentType());
        return map;
    }


}
