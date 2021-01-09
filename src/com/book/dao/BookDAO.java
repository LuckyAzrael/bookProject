package com.book.dao;

import com.book.bean.Book;

import java.util.List;

/**
 * @InterfaceName BookDAO
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 21:24
 * @Version 1.0
 **/
public interface BookDAO {
    /**
     * 添加图书
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 修改图书信息
     * @param book
     * @return
     */
    int updateBook(Book book);

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
     * 返回总记录数
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 返回价格区间内的总记录数
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 查询分页后的图书信息
     * @return
     */
    List<Book> queryForPageItems(int begin,int pageSize);

    /**
     * 查询价格区间内分页后的图书信息
     * @return
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
