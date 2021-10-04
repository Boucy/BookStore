package com.boucy.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("book_comments")
public class BookComments {
    @TableField("book_id")
    private Integer bookid;
    @TableField("user_id")
    private Integer userid;
    @TableField("comments")
    private String comments;
    @TableField("good")
    private Integer good;
    @TableField("bad")
    private Integer bad;

    @TableField(exist = false,insertStrategy =FieldStrategy.IGNORED,updateStrategy = FieldStrategy.IGNORED)
    private Book book;
}
