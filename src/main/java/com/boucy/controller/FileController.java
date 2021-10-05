package com.boucy.controller;

import com.boucy.mapper.BookMapper;
import com.boucy.service.BookService;
import com.boucy.service.FileService;
import com.boucy.service.UserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private FileService fileService;


    @RequestMapping("/headPhotoUpload")
    @ResponseBody
    public Map<String, String> uploadHeadPhoto(MultipartFile headPhoto, HttpServletRequest request) throws IOException {
        return fileService.uploadHeadPhoto(headPhoto, request);
    }

    @RequestMapping("/bookPhotoUpload")
    @ResponseBody
    public Map<String, String> bookPhotoUpload(MultipartFile bookPhoto, HttpServletRequest request) throws IOException {
        return fileService.bookPhotoUpload(bookPhoto, request);
    }

    @RequestMapping("/bookUpload")
    @ResponseBody
    public Map<String, String> bookUpload(MultipartFile bookFile, HttpServletRequest request) throws IOException {
        return fileService.bookUpload(bookFile, request);
    }

    @RequestMapping("/downloadBook")
    public void downloadBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean checkDownloadBook = fileService.checkDownloadBook(request, response);
        if (checkDownloadBook == true) {
            fileService.downloadBook(request, response);
        }
    }

}
