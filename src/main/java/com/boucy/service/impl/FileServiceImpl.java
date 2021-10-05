package com.boucy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boucy.mapper.BookMapper;
import com.boucy.mapper.BookPossesMapper;
import com.boucy.pojo.Book;
import com.boucy.pojo.BookPosses;
import com.boucy.pojo.User;
import com.boucy.service.FileService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    //    头像文件存储位置
    private final static String HEADFILESSERVER = "http://127.0.0.1:8090/uploadHeadPhoto/";
    //    图书封面存储位置
    private final static String BOOKPHOTOFILESERVER = "http://127.0.0.1:8090/uploadBookPhoto/";
    //    图书存储位置
    private final static String BOOKFILESERVER = "http://127.0.0.1:8090/uploadBook/";

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookPossesMapper bookPossesMapper;

    @Override
    public void downloadBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookid = request.getParameter("bookID");
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("id", bookid));
        String fileName = book.getFileName();
        String fileType = book.getFileType();
//        设置响应头
//        告诉浏览器要将数据保存到磁盘上，不在浏览器上直接解析
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        告诉浏览器下载类型
        response.setContentType(fileType);
//        获取文件一个输入流
        InputStream inputStream = new URL(BOOKFILESERVER + fileName).openStream();
//        获取一个指向浏览器的输出流
        ServletOutputStream outputStream = response.getOutputStream();
//        向浏览器相应文件即可
        IOUtils.copy(inputStream, outputStream);
    }

    @Override
    public Map<String, String> uploadHeadPhoto(MultipartFile headPhoto, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<>();
//        控制文件大小
//        if (headPhoto.getSize() > 1024 * 1024 * 5) {
//            map.put("message", "文件大小不能超过5MB");
//            return map;
//        }

//        获取文件名
        String originalFilename = headPhoto.getOriginalFilename();
//        避免文件冲突,使用UUID替换文件名
        String uuid = UUID.randomUUID().toString();
//        获取拓展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));

//        控制文件上传类型
//        if(!extendName.equals(".pdf")){
//            map.put("message","文件类型必须是.pdf");
//            return map;
//        }

//        新的文件名
        String newFileName = uuid.concat(extendName);

//        创建sun公司提供的jersey包下的client对象
        Client client = Client.create();
        WebResource resource = client.resource(HEADFILESSERVER + newFileName);
//        文件保存到另一个服务器上去了
        resource.put(String.class, headPhoto.getBytes());

//        上传成功之后，把文件的名字和文件的类型返回给浏览器
        map.put("message", "上传成功");
//        附加文件名字
        map.put("newFileName", newFileName);
//        附加文件类型
        map.put("fileType", headPhoto.getContentType());
        return map;
    }

    @Override
    public Map<String, String> bookPhotoUpload(MultipartFile bookPhoto, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<>();
//        控制文件大小
//        if (headPhoto.getSize() > 1024 * 1024 * 5) {
//            map.put("message", "文件大小不能超过5MB");
//            return map;
//        }

//        获取文件名
        String originalFilename = bookPhoto.getOriginalFilename();
//        避免文件冲突,使用UUID替换文件名
        String uuid = UUID.randomUUID().toString();
//        获取拓展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));

//        控制文件上传类型
//        if(!extendName.equals(".pdf")){
//            map.put("message","文件类型必须是.pdf");
//            return map;
//        }

//        新的文件名
        String newFileName = uuid.concat(extendName);

//        创建sun公司提供的jersey包下的client对象
        Client client = Client.create();
        WebResource resource = client.resource(BOOKPHOTOFILESERVER + newFileName);
//        文件保存到另一个服务器上去了
        resource.put(String.class, bookPhoto.getBytes());

//        上传成功之后，把文件的名字和文件的类型返回给浏览器
        map.put("message", "上传成功");
//        附加文件名字
        map.put("newFileName", newFileName);
//        附加文件类型
        map.put("fileType", bookPhoto.getContentType());
        return map;
    }

    @Override
    public Map<String, String> bookUpload(MultipartFile bookFile, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<>();
//        控制文件大小
//        if (headPhoto.getSize() > 1024 * 1024 * 5) {
//            map.put("message", "文件大小不能超过5MB");
//            return map;
//        }

//        获取文件名
        String originalFilename = bookFile.getOriginalFilename();
//        避免文件冲突,使用UUID替换文件名
        String uuid = UUID.randomUUID().toString();
//        获取拓展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));

//        控制文件上传类型
        if (!extendName.equals(".pdf")) {
            map.put("message", "文件类型必须是.pdf");
            return map;
        }

//        新的文件名
        String newFileName = uuid.concat(extendName);

//        创建sun公司提供的jersey包下的client对象
        Client client = Client.create();
        WebResource resource = client.resource(BOOKFILESERVER + newFileName);
//        文件保存到另一个服务器上去了
        resource.put(String.class, bookFile.getBytes());

//        上传成功之后，把文件的名字和文件的类型返回给浏览器
        map.put("message", "上传成功");
//        附加文件名字
        map.put("newFileName", newFileName);
//        附加文件类型
        map.put("fileType", bookFile.getContentType());
        return map;
    }

    @Override
    public boolean checkDownloadBook(HttpServletRequest request, HttpServletResponse response) {
        String bookid = request.getParameter("bookID");
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("id", bookid));
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return false;
        }
        Integer userId = user.getId();
        List<BookPosses> bookPosses = bookPossesMapper.selectList(new QueryWrapper<BookPosses>().eq("user_id", userId).eq("book_id", bookid));
        if(bookPosses.size()==0&&(!user.getUserType().equals(1))) {
            return false;
        }else{
            return true;
        }
    }
}
