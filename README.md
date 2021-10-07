# BookStore
一个图书商店


首页地址：http://localhost:8080/bookstore/book/bookStoreHomePage



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







数据库建表

	user用户	
id 	用户编号	int
nickname	用户名	varchar(20)
password	密码	varchar(20)
avatar_file_name	头像文件 存文件名	varchar(30)
avatar_file_type	头像文件 存文件类型	varchar(20)
user_type	类型,1是管理员2是用户	varchar(2)
balance	用户余额	double(7)
gender	性别	varchar(2)
phone	手机号	varchar(20)
email	邮箱	varchar(30)
birthday	生日	datetime
create_date	创建时间	datetime

	book图书	
id	图书id	int
name	图书名称	varchar(50)
author	图书作者	varchar(20)
isbn	ISBN编号	varchar(20)
publication_date	出版日期	datetime
type	学科分类	int
price	价格	double(7) 小数点(2)
sales	销量	int
good	点赞数	int
bad	点踩数	int
introduction	简介	text
photo_name	图书封面文件 存文件名	varchar(100)
photo_type	图书封面文件 存文件类型	varchar(20)
file_name	图书文件 存文件名	varchar(100)
file_type	图书文件 存文件类型	varchar(20)

	purchase_record购买记录	
id	订单id	int
user_id	购买人id	int
book_id	图书id	int
purchase_date	购买日期	datetime
price	购买金额	double(7) 小数点(2)

	book_posses用户所有图书	
user_id	用户id	int
book_id	图书id	int

	book_collection用户收藏图书	
user_id	用户id	int
book_id	图书id	int


	book_shopping_cart购物车	
user_id	用户id	int
book_id	图书id	int

	book_comments书评	
book_id	图书id	int
user_id	用户id	int
comments	书评	text
good	个人点赞	int
bad	个人点踩	int



 接口


url	完成进度	remark	method
book/bookSearchByKey	1	图书搜索	get
book/bookStoreHomePage	1	图书商城首页	get
book/bookSearchByID	1	单本图书搜索	get
book/bookSearchByTypeID	1	根据图书类型搜索	get
book/rankingList	1	跳转图书畅销排行榜	get
book/showBookComments	1	查看图书书评	get
book/showAddComments	1	跳转添加书评页面	get
book/addComments	1	添加书评	get
book/addBook	1	上架图书	get
book/bookDetails	1	图书详情	get
book/bookComments	1	查看图书评价	get
book/like	1	图书点赞	get
book/dislike	1	图书点踩	get
book/showCollect	1	跳转收藏界面	get
book/collect	1	图书收藏	get
book/cancelCollect	1	取消图书收藏	get
book/addShoppingCart	1	加入购物车	get
book/cancelShoppingCart	1	取消购物车	get
book/showUpdateBookPage	1	跳转修改图书界面	get
book/updateBook	1	修改图书	get
book/deleteBook	1	删除图书	get
book/purchaseBook	1
.	购买图书	get
book/purchaseMultpleBook	1	购买多本图书	get
book/showShoppingCart	1	跳转购物车	get
book/personalBook	1	个人拥有图书	get
user/jumpUserAccount	1	跳转个人账户界面	get
user/jumpPersonalInfo	1	跳转个人资料界面g	get
user/jumpRegister	1	跳转注册界面	get
user/userRegister	1	用户注册	get
user/jumpLogin	1	跳转登录界面	get
user/useridCheck	1	检查userid是否可用	get
user/loginCheck	1	登录	post
user/register	1	注册	post
user/logout	1	登出	get
user/showUserInfo	1	展示用户信息	get
user/jumpUpdateUserInfo	1	跳转修改用户信息界面	get
user/updateUserInfo	1	修改用户信息	post
user/deleteUser	1	删除用户	get
user/selectUser	1	搜索用户	get
user/showPurchaseRecord	1	展示个人购买记录	get
user/showPersonalBookComments	1	展示个人书评	get
user/showRefuseAdmin	1	跳转拒绝访问管理界面	get
manager/showBookManagement	1	跳转图书管理界面	get
manager/showUserManagement	1	跳转用户管理界面	get
manager/showPurchaseManagement	1	跳转用户购买记录界面	get
manager/showAddBookPage	1	跳转上架图书界面	get
file/bookPhotoUpload	1	上传头像	get
file/downloadAvatar		下载头像	get
file/bookUpload	1	上传图书	get
file/downloadBook	1	下载图书	get



























