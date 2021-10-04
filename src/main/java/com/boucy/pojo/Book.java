package com.boucy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("book")
public class Book {
    @TableField("id")
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("author")
    private String author;
    @TableField("isbn")
    private String isbn;
    @TableField("publication_date")
    private Date publicationDate;
    @TableField("type")
    private String type;
    @TableField("price")
    private Double price;
    @TableField("sales")
    private Integer sales;
    @TableField("good")
    private Integer good;
    @TableField("bad")
    private Integer bad;
    @TableField("introduction")
    private String introduction;
    @TableField("photo_name")
    private String photoName;
    @TableField("photo_type")
    private String photoType;
    @TableField("file_name")
    private String fileName;
    @TableField("file_type")
    private String fileType;
    @TableField(exist = false)
    private List<BookComments> bookCommentsList;
}
