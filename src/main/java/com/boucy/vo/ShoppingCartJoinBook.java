package com.boucy.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoppingCartJoinBook {
    @TableField("user_id")
    private Integer userid;
    @TableField("book_id")
    private Integer bookid;

    private Integer id;
    private String name;
    private String author;
    private String isbn;
    private Date publicationDate;
    private Double price;
    private Integer sales;
    private Integer good;
    private Integer bad;
    private String photoName;
    private String photoType;
    private String fileName;
    private String fileType;
}
