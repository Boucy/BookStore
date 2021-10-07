package com.boucy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.*;
import com.boucy.pojo.*;
import com.boucy.service.BookPossesService;
import com.boucy.service.BookService;
import com.boucy.vo.BookJoinBookPosses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class BookPossesServiceImpl extends ServiceImpl<BookPossesMapper, BookPosses> implements BookPossesService {

    @Override
    public Page<BookJoinBookPosses> getBookJoinBookPosses(Page<BookJoinBookPosses> page,HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        Integer userId = user.getId();
        return page.setRecords(this.baseMapper.getBookJoinBookPosses(page,userId.toString()));
    }
}
