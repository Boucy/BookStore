package com.boucy.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public interface FileService {
    void downloadBook(HttpServletRequest request,HttpServletResponse response) throws IOException;

    Map<String, String> uploadHeadPhoto(MultipartFile headPhoto, HttpServletRequest request) throws IOException;
}