package com.book.service.impl;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.dao.BookDAO;
import com.book.dao.impl.BookDAOIpml;
import com.book.service.BookService;


import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 22:13
 * @Version 1.0
 **/
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = new BookDAOIpml();
    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookDAO.deleteById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDAO.queryBookById(id);
    }

    @Override
    public List<Book> queryAllBooks() {
        return bookDAO.queryAllBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<Book>();

        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDAO.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDAO.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();

        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDAO.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDAO.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }


}
