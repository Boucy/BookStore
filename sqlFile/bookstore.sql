/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 08/10/2021 13:36:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isbn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publication_date` datetime(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `price` double(7, 2) NULL DEFAULT NULL,
  `sales` int(0) NULL DEFAULT NULL,
  `good` int(0) NULL DEFAULT NULL,
  `bad` int(0) NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `photo_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `photo_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (8, '数学大辞典（第二版）', '王元', '9787030533364', '2017-11-01 00:00:00', 1, 1.00, 0, 0, 0, '本书是一部综合性的数学大辞典，涵盖数理逻辑、数学基础、数论、代数学、代数几何、分析学、复分析、常微分方程、动力系统、偏微分方程、泛函分析、组合数学、图论、几何学、拓扑学、微分几何学、概率论、数理统计、计算数学、控制论、信息论、密码学、运筹学等学科，以常用、基础和重要的名词术语为基本内容，提供简短扼要的定义或概念解释，并有适度展开。正文后附有数学发展历史纪要、人名译名对照表等附录, 并设有便于检索的外文索引、汉语拼音索引', '0a0cc62d-ceed-47dc-a58c-0a2e2b68548f.jpg', 'image/jpeg', '0b6fc365-5d77-4ad4-83ae-3aa0a4a4fc45.pdf', 'application/pdf');
INSERT INTO `book` VALUES (9, '研究生数学建模精品案例', '朱道元', '9787030398444', '2014-03-01 00:00:00', 1, 1.00, 0, 0, 0, '本书共分12章，内容包括从研究生数学建模角度看创造性及创造性培养、吸波材料与微波暗室问题的数学建模、汶川地震中唐家山堰塞湖泄洪问题、特殊工件磨削加工的数学建模等。', '7a42da1f-949c-44ec-ba20-88238cb31d7f.jpg', 'image/jpeg', '0b6fc365-5d77-4ad4-83ae-3aa0a4a4fc45.pdf', 'application/pdf');
INSERT INTO `book` VALUES (10, '图论导引', '许胤龙,吕敏,李永坤', '9787030666734', '2021-01-01 00:00:00', 1, 1.00, 0, 0, 0, '本书主要分为基础知识与应用两个部分. 在基础知识部分, 系统地介绍了图论的基本概念、理论和方法, 具体内容包括图的基本概念、树、图的连通性、平面图、匹配理论、Euler 图与 Hamilton图、图的着色、有向图、网络流理论以及图矩阵与图空间，共十章. 在应用部分, 主要介绍了近年来图计算方面的一些典型应用和系统, 具体内容包括无标度图与图计算系统两章. 每章后面都附有一定数量的习题, 供读者练习和进一步思考', '5068c0fd-219b-4acb-9cbe-1663ed739633.jpg', 'image/jpeg', '0b6fc365-5d77-4ad4-83ae-3aa0a4a4fc45.pdf', 'application/pdf');
INSERT INTO `book` VALUES (11, '软材料表面失稳力学 ', ' 冯西桥,曹艳平,李博 著 ', '9787030540935', '2017-12-01 00:00:00', 2, 1.00, 0, 0, 0, '本书是一本关于软材料与生物软组织力学的专著，介绍了软材料失稳力学的基本理论、数值、实验方法及其应用，主要内容是作者在该领域取得的研究成果。全书共17章，介绍了软材料稳定性理论的国内外研究现状、基本理论与数值计算方法、在不同几何和载荷下薄膜-基底系统的失稳分析与形貌演化、基于失稳技术的表面形貌制备方法、生物软组织由于非均匀生长所引起的表面失稳与形貌演化等', 'a37cd405-fbc9-4808-823d-e502c3b35229.jpg', 'image/jpeg', '0b6fc365-5d77-4ad4-83ae-3aa0a4a4fc45.pdf', 'application/pdf');
INSERT INTO `book` VALUES (12, '碳纳米管复合材料及功能器件 ', ' 王奇观,王素敏,张文治 著 ', '9787030638649', '2020-12-01 00:00:00', 2, 1.00, 0, 0, 0, '碳纳米管是由石墨烯片层卷曲而成的无缝、中空管状结构，具有极高的强度、韧性及弹性模量，以及优良的耐热、耐腐蚀、耐热冲击、导电和导热性能。本书首先针对碳纳米管的结构特点、制备方法进行详细介绍；然后针对其难溶、难加工的特性，详细介绍为提高碳纳米管的溶解性而采取的改性方法及改性对其性能的影响；最后重点阐述碳纳米管复合材料（包括碳纳米管 /无机金属复合材料、碳纳米管 /聚合物复合材料、碳纳米管基光电复合材料）及其功能器件的种类、制备方法和在能源、光电信息、生物等领域的技术发展', '251b83d2-eaea-4fe4-8a3d-3a5b90b62b03.jpg', 'image/jpeg', 'c73f5b59-8dcf-48de-b0b1-d412d7a81bf3.pdf', 'application/pdf');
INSERT INTO `book` VALUES (13, '解码生命：从多视角看生命（上卷）', ' 贺林 主编 ', '9787030663955', '2020-12-01 00:00:00', 3, 1.00, 0, 0, 0, '《解码生命》（第二版）由“人类基因组计划及后续相关计划”“基因组计划引导生物技术的强劲发展”“当前对人类基因组的认识及其拓展”“基因组学的临床应用”“生命的合成、人工智能及其他”5篇共50章组成，涵盖了基因组学及其相关学科发展和应用的方方面面。', '094ef151-f38d-4e38-a1fe-8d4a2a31c4ba.jpg', 'image/jpeg', 'c73f5b59-8dcf-48de-b0b1-d412d7a81bf3.pdf', 'application/pdf');
INSERT INTO `book` VALUES (14, '水动力及水环境模拟方法与应用', ' 陈永灿，刘昭伟，朱德军著 ', '9787030359834', '2012-11-01 00:00:00', 5, 1.00, 0, 0, 0, '本书模拟了水流动力条件的变化规律，分析了相关的物理输移和生化反应过程，建立了水环境模拟的数学模型，预测了水温、营养盐、叶绿素、溶解氧等重要环境要素的时空变化，探究了它们在不同条件下的发展趋势等。', '54316569-a440-45b8-b3f2-208a991640e5.jpg', 'image/jpeg', 'c73f5b59-8dcf-48de-b0b1-d412d7a81bf3.pdf', 'application/pdf');
INSERT INTO `book` VALUES (15, '袁隆平论文集', '袁隆平主编', '9787030285706', '2010-09-01 00:00:00', 6, 1.00, 0, 0, 0, '本书是从袁隆平从事杂交水稻技术研发期间发表的杂交水稻研究文章和在国际会议及活动的报告中选择并结集出版的中英文论文集。内容涉及杂交水稻育种、栽培、基础研究等，集中体现了杂交水稻学的综合全貌与主要内容。', '4286e6d9-a9e0-41ae-b608-617e4dc5b117.jpg', 'image/jpeg', 'c73f5b59-8dcf-48de-b0b1-d412d7a81bf3.pdf', 'application/pdf');
INSERT INTO `book` VALUES (16, '医学统计学与SPSS软件实现方法（第二版）', ' 郭秀花 主编 ', '9787030285706', '2010-09-01 00:00:00', 7, 1.00, 0, 0, 0, ' 医学统计学是我国各医学院校所有专业本科生、研究生的必修课，也是从事医学科学研究工作者不可缺少的方法学。本教材将医学统计学理论方法与常用的SPSS软件操作相结合，分为十八章：绪论、数据管理与SPSS软件实现方法、定量资料的统计描述、定量资料的参数估计与假设检验基础、定量资料的t检验、定量资料的方差分析、定量资料的非参数检验、定性资料的统计描述、定性资料的参数估计与X2检验、有序定性资料的假设检验方法、直线相关与回归、多重线性回归分析、Logistic回归分析、生存分析基本统计方法、Cox比例风险回归分析、统计表与统计图、观察性研究设计、实验性研究设计。本教材的附录部分增加了自测试题。此外，教材中所有例题数据、各章练习题答案、自测试题答案和样本含量估计方法，采用了二维码形式，方便使用。 ', 'e710c70b-2c0c-49fb-8851-ea6f535cadfc.jpg', 'image/jpeg', 'c73f5b59-8dcf-48de-b0b1-d412d7a81bf3.pdf', 'application/pdf');
INSERT INTO `book` VALUES (17, '量子图像处理及应用 ', ' 闫飞,杨华民,蒋振刚 ', '9787030464972', '2016-05-01 00:00:00', 8, 1.00, 0, 0, 0, ' 本书针对近年新兴研究方向——量子图像处理进行了系统介绍，主要内容有：量子图像处理中的基本概念及常用的量子图像表达式、量子图像的基本信息变换及处理操作、量子图像并行比较与数据库检索方法、量子图像的安全保护策略及量子计算机上的视频表达与应用。 ', '0ae6eabe-abad-413b-b018-e0f26a695ae2.jpg', 'image/jpeg', 'c73f5b59-8dcf-48de-b0b1-d412d7a81bf3.pdf', 'application/pdf');
INSERT INTO `book` VALUES (18, '物联网工程开发与应用实例 ', ' 马洪连,朱明 ', '9787030493736', '2016-08-01 00:00:00', 8, 1.00, 0, 0, 0, ' 近几年，物联网从诞生到迅速发展，受到了产业界及学术界的广泛重视。本书从物联网工程实际应用的角度出发，针对国内大专院校物联网工程专业课程群实践教学的需要，结合作者多年的教学、科研方面的经验编写了这部物联网工程开发与应用实例教材。本书以基于四核Cortex-A9 微处理器为核心的物联网综合教学实验平台为物联网工程应用实例的硬件平台，该平台还配有以CC2530 为核心部件的14 种无线感知、识别、控制节点模块以及摄像头、条形码和指纹识别三种外设。针对物联网工程实验教学与培训体系不同层面的需要，本教材精心设计和开发了36 项应用实例。', '882b58c0-6f1e-4d40-bee8-7bc68df40c42.jpg', 'image/jpeg', 'd412d7a81bf3.pdf', 'd412d7a81bf3.pdf');
INSERT INTO `book` VALUES (19, ' 筑梦天宫：从万户飞天到中国空间站 ', ' 中国科学院空间应用工程与技术中心,新华社对外部中国特稿社 ', '9787030503077', '2016-11-01 00:00:00', 9, 1.00, 0, 0, 0, ' 2016年9月，随着天宫二号的顺利升空，中国空间站建设的大幕正式拉开。本书以通俗易懂的语言、精美生动的图片，记录了中国人从万户飞天以来的太空探索历程，描述了在中国首个太空实验室天宫二号上进行的诸多意义重大的科学实验，向广大读者立体呈现出了空间科学实验的无穷魅力。同时，全书描写刻画了多位中国空间研究与应用科学家代表，展现了我国科学家不断追求卓越、勇于创新实践的科学精神。 ', 'c50a878f-d9aa-480f-8996-c160f7d94e4a.jpg', 'image/jpeg', 'd412d7a81bf3.pdf', 'd412d7a81bf3.pdf');
INSERT INTO `book` VALUES (20, ' 筑梦天宫：发达国家科技计划管理机制研究 ', ' 中国科学院空间应用工程与技术中心,发达国家科技计划管理机制研究课题组', '9787030477446', '2016-04-01 00:00:00', 10, 1.00, 0, 0, 0, '发达国家将科技计划作为组织国家科技活动的重要形式，在长期实践中积累了丰富经验，对我国正在进行的科技计划体系改革具有重要的借鉴意义。 本书由中国科学院发展规划局和中国科学院文献情报中心组织编写，对发达国家在生命科学、环填与生态、能源、资源海洋、制造业、农业等领域的重大科技计划的发起、立项、组织运行、项目管理、过程监督、后期评估及科研项目的管理和科技计划专业管理机构的组织运行机制进行分析研究。在国别和领域调研的基础上总结共性问题和规律，形成了发这国家科技计划的宏观管理机制、专业管理机构运行机制、项目管理机制的研究结果。 ', '4c035a8a-73d2-41a2-9f9d-9994e0e4b291.jpg', 'image/jpeg', 'd412d7a81bf3.pdf', 'd412d7a81bf3.pdf');
INSERT INTO `book` VALUES (21, ' 中华文明之源：从史前到先秦 ', '吴涛著', '9787030631787', '2019-12-01 00:00:00', 11, 1.00, 0, 0, 0, ' 先秦是中华文明起源的时期,先秦史始终是中国史研究的重点。《中华文明之源：从史前到先秦》在充分吸收前人研究成果的基础上,尝试从文明发展的角度对先秦史进行整体的梳理。文明的诞生并不是一蹴而就的,它经历了一个漫长的历史过程。《中华文明之源：从史前到先秦》以“长时段”,从宏观的角度对中华文明的萌发和其早期发展历程进行考察。首先对中华文明起源的单一源头说和多源说进行辨析,提出自己的观点。接着按照历史发展的顺序,揭示中华文明早期发展的历史进程和中华文明发展的历史规律,以期深化相关研究,更好地凝聚民族认同和文化认同。 ', '6ffee98f-2b63-472e-879e-0527cd7f021f.jpg', 'image/jpeg', 'd412d7a81bf3.pdf', 'd412d7a81bf3.pdf');
INSERT INTO `book` VALUES (22, '自旋电子学导论（上卷） ', '韩秀峰等', '9787030428264', '2015-01-01 00:00:00', 1, 24.00, 0, 0, 0, ' 本书由工作在自旋电子学研究领域里的国内外50余位学者撰写而成。全书分两卷、共28章，各章均由该领域富有研究经验的知名专家负责，较全面地介绍和论述了目前自旋电子学研究领域中的各个重要研究方向及其进展，并重点关注自旋电子学的关键材料探索、物理效应研究及其原理型器件的设计开发和实际应用。 本书适合物理（特别是自旋电子学）及相关领域的大学本科高年级学生、研究生、教师、工程师和科研工作者等参考阅读。” ', 'f142a2f6-c46d-41a3-98a7-d4a1095bfe07.jpg', 'image/jpeg', 'd412d7a81bf3.pdf', 'd412d7a81bf3.pdf');
INSERT INTO `book` VALUES (23, '导电聚合物电化学 ', ' 李永舫,穆绍林 著 ', '9787030670168', '2020-12-01 00:00:00', 2, 52.00, 0, 0, 0, ' 本书由工作在自旋电子学研究领域里的国内外50余位学者撰写而成。全书分两卷、共28章，各章均由该领域富有研究经验的知名专家负责，较全面地介绍和论述了目前自旋电子学研究领域中的各个重要研究方向及其进展，并重点关注自旋电子学的关键材料探索、物理效应研究及其原理型器件的设计开发和实际应用。 本书适合物理（特别是自旋电子学）及相关领域的大学本科高年级学生、研究生、教师、 导电聚合物是20世纪70年代发展起来的一个重要研究领域，电化学聚合是导电聚合物的一种重要制备方法，电化学性质是导电聚合物最重要的性质之一。本书系统介绍导电聚合物电化学的基础知识和应用，包括导电聚合物的发现和发展历史及其基本性质，聚吡咯、聚苯胺和聚噻吩这三种最重要的导电聚合物的电化学制备和电化学性质，导电聚合物在电化学生物传感、化学电源、电化学超电容、电致变色器件和电催化等与其电化学性质相关的领域的应用，与其电化学聚合相关的有机发光薄膜的电化学制备，以及与其电化学掺杂性质相关的聚合物发光电化学池和共轭聚合物电子能级的电化学测量等。 。” ', 'fa6b3013-9082-41d9-aac1-779693037286.jpg', 'image/jpeg', '631229ba-1fea-46c4-8733-75693c1b8310.pdf', 'application/pdf');
INSERT INTO `book` VALUES (24, ' 气体同位素质谱分析300问 ', ' 曹亚澄等 编著 ', '9787030665058', '2020-11-01 00:00:00', 2, 5.00, 0, 0, 0, ' 气体同位素质谱仪是现代科学研究的一种先进仪器设备，且与同位素质谱仪联机的外部进样设备又多种多样。在仪器性能、分析方法和样品处理技术快速发展的今天，无论是在仪器的使用还是测试方法的掌握上出现很多问题是必然的，在很多场合经常有人提出质谱分析中遇到的许多仪器故障或方法技术问题，请求解答或想得到正确的解决方法。为此，本书将收集到的这些杂然无章的问题进行分类和整理，并尽可能给出恰当的答案。这是编写出版《气体同位素质谱分析300问》一书的初心。本书分类整理出六章，共收集了341个问题，以问答的方式进行编写，图文并茂，清晰明了，通俗易懂。 ', '56f13951-552d-4801-90a6-4a1f8337a8c7.jpg', 'image/jpeg', '22bb8a62-7907-4f90-9353-6e12f8844e7d.pdf', 'application/pdf');

-- ----------------------------
-- Table structure for book_collection
-- ----------------------------
DROP TABLE IF EXISTS `book_collection`;
CREATE TABLE `book_collection`  (
  `user_id` int(0) NULL DEFAULT NULL,
  `book_id` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_collection
-- ----------------------------
INSERT INTO `book_collection` VALUES (1, 10);

-- ----------------------------
-- Table structure for book_comments
-- ----------------------------
DROP TABLE IF EXISTS `book_comments`;
CREATE TABLE `book_comments`  (
  `book_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `comments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `good` int(0) NULL DEFAULT NULL,
  `bad` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_comments
-- ----------------------------
INSERT INTO `book_comments` VALUES (10, 1, '666', 1, 0);
INSERT INTO `book_comments` VALUES (8, 1, '111', 0, 0);
INSERT INTO `book_comments` VALUES (8, 1, '2222', 0, 0);

-- ----------------------------
-- Table structure for book_posses
-- ----------------------------
DROP TABLE IF EXISTS `book_posses`;
CREATE TABLE `book_posses`  (
  `user_id` int(0) NULL DEFAULT NULL,
  `book_id` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_posses
-- ----------------------------
INSERT INTO `book_posses` VALUES (1, 9);
INSERT INTO `book_posses` VALUES (1, 11);
INSERT INTO `book_posses` VALUES (1, 12);
INSERT INTO `book_posses` VALUES (1, 17);
INSERT INTO `book_posses` VALUES (1, 18);

-- ----------------------------
-- Table structure for book_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `book_shopping_cart`;
CREATE TABLE `book_shopping_cart`  (
  `user_id` int(0) NULL DEFAULT NULL,
  `book_id` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_shopping_cart
-- ----------------------------
INSERT INTO `book_shopping_cart` VALUES (1, 13);
INSERT INTO `book_shopping_cart` VALUES (1, 15);

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (1, '数理');
INSERT INTO `book_type` VALUES (2, '化学材料');
INSERT INTO `book_type` VALUES (3, '生命');
INSERT INTO `book_type` VALUES (4, '地球');
INSERT INTO `book_type` VALUES (5, '资源环境');
INSERT INTO `book_type` VALUES (6, '农林');
INSERT INTO `book_type` VALUES (7, '医药');
INSERT INTO `book_type` VALUES (8, '信息');
INSERT INTO `book_type` VALUES (9, '工程');
INSERT INTO `book_type` VALUES (10, '管理');
INSERT INTO `book_type` VALUES (11, '历史考古');

-- ----------------------------
-- Table structure for purchase_record
-- ----------------------------
DROP TABLE IF EXISTS `purchase_record`;
CREATE TABLE `purchase_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `book_id` int(0) NULL DEFAULT NULL,
  `purchase_date` datetime(0) NULL DEFAULT NULL,
  `price` double(7, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_record
-- ----------------------------
INSERT INTO `purchase_record` VALUES (5, 1, 9, '2021-10-06 10:03:19', 1.00);
INSERT INTO `purchase_record` VALUES (6, 1, 8, '2021-10-06 10:03:20', 1.00);
INSERT INTO `purchase_record` VALUES (7, 1, 9, '2021-10-06 10:04:42', 1.00);
INSERT INTO `purchase_record` VALUES (8, 1, 8, '2021-10-06 10:04:42', 1.00);
INSERT INTO `purchase_record` VALUES (9, 1, 9, '2021-10-06 10:07:39', 1.00);
INSERT INTO `purchase_record` VALUES (10, 1, 8, '2021-10-06 10:07:39', 1.00);
INSERT INTO `purchase_record` VALUES (11, 1, 9, '2021-10-06 10:09:17', 1.00);
INSERT INTO `purchase_record` VALUES (12, 1, 11, '2021-10-08 00:19:13', 1.00);
INSERT INTO `purchase_record` VALUES (13, 1, 12, '2021-10-08 00:19:13', 1.00);
INSERT INTO `purchase_record` VALUES (14, 1, 17, '2021-10-08 00:19:24', 1.00);
INSERT INTO `purchase_record` VALUES (15, 1, 18, '2021-10-08 00:19:24', 1.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL,
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_file_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `balance` double(7, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '管理员', '1', '52e24f9a-9549-47bf-977b-8f810d9d6c6c.jpg', 'image/jpeg', '1', 0996.00, '男', '1', '1', '1999-01-02 00:00:00', '2021-10-08 18:00:00');
INSERT INTO `user` VALUES (2, '用户', '2', 'e25947b3-255f-4f55-99c2-b83a92e947ab.jpg', 'image/jpeg', '2', 0994.00, '2', '2', '2', '2001-01-01 00:00:00', '2021-09-30 20:21:26');
INSERT INTO `user` VALUES (3, '三三', '3', 'e4874d50-1a64-40c9-bcd7-5ce302165cfe.png', 'image/png', '2', 0000.00, '男', '123456789', '64901870@qq.com', '2003-02-02 00:00:00', '2021-10-08 13:14:44');

SET FOREIGN_KEY_CHECKS = 1;
