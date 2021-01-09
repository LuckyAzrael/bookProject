package com.book.service;

import com.book.bean.Book;
import com.book.bean.Page;

import java.util.List;

/**
 * @InterfaceName BookService
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 22:07
 * @Version 1.0
 **/
public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    void addBook(Book book);

    /**
     * 根据指定id删除图书
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改图书信息
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据指定id查询图书信息
     * @param id
     * @return
     */
    Book queryBookById(Integer id);

    /**
     * 查询所有图书信息
     * @return
     */
    List<Book> queryAllBooks();

    /**
     * 查询分页的图书信息
     * @return
     */
    Page<Book> page(Integer pageNo, Integer pageSize);

    /**
     * 查询通过价格区间查询后分页的图书信息
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
