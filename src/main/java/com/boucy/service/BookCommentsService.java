package com.boucy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.pojo.BookComments;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BookCommentsService extends IService<BookComments> {
    String showPersonalBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String showBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);

    String addComments(String bookid,String good, String bad, String comments, HttpServletRequest request, HttpServletResponse response);
}
