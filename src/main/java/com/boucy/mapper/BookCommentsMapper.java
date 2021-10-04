package com.boucy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.BookComments;
import com.boucy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookCommentsMapper extends BaseMapper<BookComments> {
    public Book findBookJoinBookCommentsByBookid(int bookid);

    public List<BookComments> findBookCommentsJoinBookByUserid(int userid);

    @Insert("insert into book_comments values(#{bookComments.bookid},#{bookComments.userid},#{bookComments.comments},#{bookComments.good},#{bookComments.bad})")
    public void addComments(BookComments bookComments);
}
