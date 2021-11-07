# BookStore


#### 介绍
图书商店课设

首页地址：http://localhost:8080/bookstore/book/bookStoreHomePage

关于文件上传下载问题,目前项目文件均保存到
http://127.0.0.1:8090/uploadHeadPhoto/
http://127.0.0.1:8090/uploadBookPhoto/
http://127.0.0.1:8090/uploadBook/
使用需要用本地tomcat开启一个8090端口号，并且创建三个目录



为用户提供了可根据自身业务需求选择的多个功能模块
商城
	商城模块：
1.	图书搜索：通过书名、作者、书籍分类、ISBN码
2.	图书收藏、加入购物车
3.	图书畅销排行榜
4.	图书分类
5.	图书书评
6.	图书点赞、点踩

个人用户
	购物车模块：
1.	查询，单本或多本购买
	图书收藏模块：
1.	查询
	个人图书：
1.	查询，收藏，下载
	个人账户：
1.	查看、修改个人信息
2.	头像上传下载
3.	购买记录
4.	账户余额
5.	个人书评

后台管理
	用户管理模块：
1.	用户账号信息增删查改
2.	用户购买记录查询
3.	用户账号注册、登录、退出
	图书管理模块：图书属性限制为pdf文件
1.	图书下载、上架、删除、修改、查看、分类

在线图书商店目前包括三个服务端：管理员端、用户端、游客端，使用者可以根据情况选择其中之一：
	
		管理员端：
1.	后台管理界面
2.	上架、下架、管理图书信息
3.	管理用户信息、购买记录
		用户端：
1.	注册，登录，登出
2.	收藏图书：增加删除
3.	购物车：增加删除
4.	用户能购买单本或多本图书
5.	用户能通过书名、作者、书籍分类、ISBN码来搜索图书
6.	用户能查看自己购买了哪些图书
7.	用户能够对某一图书进行评价，能够查询自己的所有评价
	游客端：
1.	游客能使用图书商店（查看、搜索）
2.	当游客使用需要登录才能使用的功能（购买、收藏、加入购物车等）的时候，提示登录




#### 软件架构
##### - 后端
JDK版本：8

web框架：SpringBoot 2.5.5.RELEASE

数据库：Mysql8

数据库连接池：druid

数据库操作框架：Mybatis 2.2.0、mybatis-plus

权限管理框架：Shiro 1.4

日志框架：logback

API访问框架：swagger2

##### - 前端

模板引擎:Thymeleaf 2.5.5

HTML

AJAX

#### 开发说明

- 必须在dev分支下开发
- 功能开发完毕之后再通过merge命令合并到master分支上
- 再提交提交代码前注意代码的格式化
    - Java程序采用驼峰命名法，每个变量名都要能够大概看出其含义，不允许使用拼音缩写！！！
    - 程序规范采用阿里的编码规约，在IDEA中可以搜索《Alibaba Java Coding Guidelines》进行插件的安装

- 在push到远程时可以先pull拉取远程的项目，如果有冲突解决冲突后再提交

#### 项目目录介绍
```aidl
com.boucy       源文件根目录
├─ config                   配置类包
├─ controller               控制器类所在的包
├─ dao                      数据访问接口所在的包
├─ domain                   实体类所在的包
├─ service                  服务层接口所在的包
    ├─ serviceImpl          服务层接口实现类所在的包
├─ shiro                    Shiro的Realm所在的包
├─ utils                    工具类包
├─ Application.java         项目启动类
resources                   资源目录
├─ mappers                  mybatis的mapper文件所在的目录
├─ application.yml          项目的总配置类
```



