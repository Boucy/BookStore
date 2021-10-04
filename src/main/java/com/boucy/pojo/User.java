package com.boucy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
public class User {
    @TableField("id")
    private Integer id;
    @TableField("nickname")
    private String nickname;
    @TableField("password")
    private String password;
    @TableField("avatar_file_name")
    private String avatarFileName;
    @TableField("avatar_file_type")
    private String avatarFileType;
    @TableField("user_type")
    private String userType;
    @TableField("balance")
    private Double balance;
    @TableField("gender")
    private String gender;
    @TableField("phone")
    private String phone;
    @TableField("email")
    private String email;
    @TableField("birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @TableField("create_date")
    private Date createDate;
}
