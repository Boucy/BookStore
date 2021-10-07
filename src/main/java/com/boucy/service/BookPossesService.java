package com.boucy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boucy.pojo.Book;
import com.boucy.pojo.BookPosses;
import com.boucy.vo.BookJoinBookPosses;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface BookPossesService extends IService<BookPosses> {
    Page<BookJoinBookPosses> getBookJoinBookPosses(Page<BookJoinBookPosses> page);
}
