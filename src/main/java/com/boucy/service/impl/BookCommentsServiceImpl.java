package com.boucy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.BookCommentsMapper;
import com.boucy.mapper.BookMapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.BookComments;
import com.boucy.pojo.User;
import com.boucy.service.BookCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class BookCommentsServiceImpl extends ServiceImpl<BookCommentsMapper, BookComments> implements BookCommentsService {
    @Autowired
    private BookCommentsMapper bookCommentsMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public String showBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String bookid = request.getParameter("bookID");
        Book book = bookCommentsMapper.findBookJoinBookCommentsByBookid(Integer.parseInt(bookid));
        map.put("book", book);
        return "bookComments";
    }

    @Override
    public String addComments(String bookid,String good, String bad, String comments, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        BookComments bookComments = new BookComments(Integer.parseInt(bookid), userId, comments, Integer.parseInt(good), Integer.parseInt(bad), null);
        bookCommentsMapper.insert(bookComments);
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<Book>().eq("id", bookid);
        Book book = bookMapper.selectOne(bookQueryWrapper);
        book.setGood(book.getGood()+Integer.parseInt(good));
        book.setBad(book.getBad()+Integer.parseInt(bad));
        bookMapper.update(book,bookQueryWrapper);
        return "redirect:../user/showPersonalBookComments";
    }

    @Override
    public String showPersonalBookComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        List<BookComments> bookCommentsJoinBookList = bookCommentsMapper.findBookCommentsJoinBookByUserid(userId);
        map.put("bookCommentsJoinBookList",bookCommentsJoinBookList);
        return "personalBookComments";
    }
}
