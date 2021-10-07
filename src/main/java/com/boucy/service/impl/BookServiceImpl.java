package com.boucy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boucy.mapper.*;
import com.boucy.pojo.*;
import com.boucy.service.BookService;
import com.boucy.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Autowired
    private BookShoppingCartMapper bookShoppingCartMapper;
    @Autowired
    private BookCollectionMapper bookCollectionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookPossesMapper bookPossesMapper;
    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;
    @Autowired
    private BookCommentsMapper bookCommentsMapper;

    @Override
    public String bookSearchByTypeID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        String bookTypeID = request.getParameter("bookTypeID");
        String index = request.getParameter("index");
        queryWrapper.eq("type", bookTypeID);
        Page<Book> page = bookMapper.selectPage(new Page<>(Integer.parseInt(index), 5), queryWrapper);
        List<Book> bookList = page.getRecords();
        map.put("bookTypeID",bookTypeID);
        map.put("bookList", bookList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "showBooks";
    }

    @Override
    public String bookSearchByID(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", request.getParameter("bookID"));
        Book book = bookMapper.selectOne(queryWrapper);
        QueryWrapper<BookType> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", book.getType());
        BookType bookType = bookTypeMapper.selectOne(queryWrapper1);
        map.put("book", book);
        map.put("bookType", bookType.getType());
        return "showBook";
    }

    @Override
    public String collect(HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<BookCollection> bookCollectionQueryWrapper = new QueryWrapper<>();
        bookCollectionQueryWrapper.eq("book_id", bookID);
        BookCollection bookCollection = bookCollectionMapper.selectOne(bookCollectionQueryWrapper);
        if (bookCollection == null) {
            bookCollectionMapper.insert(new BookCollection(userid, Integer.parseInt(bookID)));
        }
        return "redirect:../book/showCollect";
    }

    @Override
    public String cancelCollect(HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<BookCollection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userid).eq("book_id", bookID);
        bookCollectionMapper.delete(queryWrapper);
        return "redirect:../book/showCollect";
    }

    @Override
    public String showCollect(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<BookCollection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userid);
        List<Book> bookList = new ArrayList<>();
        for (BookCollection bookCollection : bookCollectionMapper.selectList(queryWrapper)) {
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("id", bookCollection.getBookid());
            Book book = bookMapper.selectOne(bookQueryWrapper);
            bookList.add(book);
        }
        map.put("bookList", bookList);
        return "bookCollect";
    }

    @Override
    public String rankingList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String index = request.getParameter("index");
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sales");
        Page<Book> page = bookMapper.selectPage(new Page<>(Integer.parseInt(index), 5), queryWrapper);
        List<Book> bookList = page.getRecords();
//        页数据
        map.put("bookList", bookList);
//        总记录数
        map.put("total", page.getTotal());
//        总页数
        map.put("pageCount", page.getPages());
//        当前页
        map.put("pageIndex", page.getCurrent());
//        页大小
        map.put("pageSize", page.getSize());
        return "showRankingList";
    }

    @Override
    public String bookSearchByKey(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String keywords = request.getParameter("keywords");
        List<Book> bookList = new ArrayList<>();
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("isbn", keywords));
        if (book != null) {
            bookList.add(book);
        }
        book = bookMapper.selectOne(new QueryWrapper<Book>().eq("name", keywords));
        if (book != null) {
            bookList.add(book);
        }
        List<Book> list = bookMapper.selectList(new QueryWrapper<Book>().like("author", keywords));
        Iterator<Book> iterator1 = list.iterator();
        while (iterator1.hasNext()) {
            book = iterator1.next();
            bookList.add(book);
        }

        List<Book> books = bookMapper.selectList(new QueryWrapper<Book>().like("name", keywords));
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            bookList.add(book);
        }
        for (int i = 0; i < bookList.size() - 1; i++) {
            for (int j = bookList.size() - 1; j > i; j--) {
                if (bookList.get(j).getId().equals(bookList.get(i).getId())) {
                    bookList.remove(j);
                }
            }
        }
        map.put("bookList", bookList);
        return "showBooks";
    }

    @Override
    @Transactional
    public String purchaseBook(HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("id", bookID);
        Book book = bookMapper.selectOne(bookQueryWrapper);
        User user = (User) request.getSession().getAttribute("user");
        Double balance = user.getBalance();
        Double price = book.getPrice();
        if (balance >= price) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("id", user.getId());
            user.setBalance(balance - price);
            userMapper.update(user, userQueryWrapper);
            bookPossesMapper.insert(new BookPosses(user.getId(), Integer.parseInt(bookID)));
            request.getSession().setAttribute("user", user);
            purchaseRecordMapper.insert(new PurchaseRecord(null, user.getId(), book.getId(), new Date(), book.getPrice(), null));
            return "redirect:../book/personalBook";
        } else {
            return "purchaseFail";
        }
    }

    @Override
    public String personalBook(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        QueryWrapper<BookPosses> bookPossesQueryWrapper = new QueryWrapper<>();
        bookPossesQueryWrapper.eq("user_id", userId);
        List<Book> bookList = new ArrayList<>();
        for (BookPosses bookPosses : bookPossesMapper.selectList(bookPossesQueryWrapper)) {
            Integer bookid = bookPosses.getBookid();
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("id", bookid);
            Book book = bookMapper.selectOne(bookQueryWrapper);
            bookList.add(book);
        }
        map.put("bookList", bookList);
        return "showBooks";
    }

    @Override
    public String showAddComments(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("id", bookID);
        Book book = bookMapper.selectOne(bookQueryWrapper);
        map.put("book", book);
        return "AddCommentsPage";
    }

    @Override
    public String showBookManagement(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        List<Book> bookList = bookMapper.selectList(null);
        map.put("bookList", bookList);
        return "bookManagement";
    }

    @Override
    public String showUpdateBookPage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("id", bookID));
        map.put("book", book);
        return "updateBookPage";
    }

    @Override
    public String updateBook(String originBookID, Book book, HttpServletRequest request, HttpServletResponse response) {
        bookMapper.update(book, new QueryWrapper<Book>().eq("id", originBookID));
        return "redirect:../book/bookSearchByID?bookID=" + originBookID;
    }

    @Override
    public String deleteBook(HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        bookMapper.delete(new QueryWrapper<Book>().eq("id", bookID));
        bookCollectionMapper.delete(new QueryWrapper<BookCollection>().eq("book_id", bookID));
        bookCommentsMapper.delete(new QueryWrapper<BookComments>().eq("book_id", bookID));
        bookPossesMapper.delete(new QueryWrapper<BookPosses>().eq("book_id", bookID));
        bookShoppingCartMapper.delete(new QueryWrapper<BookShoppingCart>().eq("book_id", bookID));
        return "redirect:../manager/showBookManagement";
    }

    @Override
    public String addBook(Book book, HttpServletRequest request, HttpServletResponse response) {
        bookMapper.insert(book);
        return "redirect:../manager/showBookManagement";
    }

    @Override
    @Transactional
    public String purchaseMultpleBook(String[] booksID, HttpServletRequest request, HttpServletResponse response) {
        List<String> bookIDList = Arrays.asList(booksID);
        Iterator<String> iterator = bookIDList.iterator();
        while (iterator.hasNext()) {
            String bookID = iterator.next();
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("id", bookID);
            Book book = bookMapper.selectOne(bookQueryWrapper);
            User user = (User) request.getSession().getAttribute("user");
            Double balance = user.getBalance();
            Double price = book.getPrice();
            if (balance >= price) {
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("id", user.getId());
                user.setBalance(balance - price);
                userMapper.update(user, userQueryWrapper);
                bookPossesMapper.insert(new BookPosses(user.getId(), Integer.parseInt(bookID)));
                request.getSession().setAttribute("user", user);
                purchaseRecordMapper.insert(new PurchaseRecord(null, user.getId(), book.getId(), new Date(), book.getPrice(), null));
            } else {
                return "purchaseFail";
            }
        }
        return "redirect:../book/personalBook";
    }

    @Override
    public String addShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<BookShoppingCart> bookShoppingCartQueryWrapper = new QueryWrapper<>();
        bookShoppingCartQueryWrapper.eq("book_id", bookID);
        BookShoppingCart bookShoppingCart = bookShoppingCartMapper.selectOne(bookShoppingCartQueryWrapper);
        if (bookShoppingCart == null) {
            bookShoppingCartMapper.insert(new BookShoppingCart(userid, Integer.parseInt(bookID)));
        }
        return "redirect:../book/showShoppingCart";
    }

    @Override
    public String cancelShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        String bookID = request.getParameter("bookID");
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<BookShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userid).eq("book_id", bookID);
        bookShoppingCartMapper.delete(queryWrapper);
        return "redirect:../book/showShoppingCart";
    }

    @Override
    public String showShoppingCart(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        QueryWrapper<BookShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userid);
        List<Book> bookList = new ArrayList<>();
        for (BookShoppingCart bookShoppingCart : bookShoppingCartMapper.selectList(queryWrapper)) {
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("id", bookShoppingCart.getBookid());
            Book book = bookMapper.selectOne(bookQueryWrapper);
            bookList.add(book);
        }

        for (int i = 0; i < bookList.size() - 1; i++) {
            for (int j = bookList.size() - 1; j > i; j--) {
                if (bookList.get(j).getId().equals(bookList.get(i).getId())) {
                    bookList.remove(j);
                }
            }
        }

        map.put("bookList", bookList);
        return "shoppingCart";
    }
}
