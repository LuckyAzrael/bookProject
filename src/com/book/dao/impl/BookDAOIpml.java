package com.book.dao.impl;

import com.book.bean.Book;
import com.book.dao.BaseDAO;
import com.book.dao.BookDAO;
import com.book.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName BookDAOipml
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 21:30
 * @Version 1.0
 **/
public class BookDAOIpml extends BaseDAO implements BookDAO {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,img_path)values(?,?,?,?,?,?)";
        return update( sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryAllBooks() {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,price,author,sales,stock,img_path imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql,min,max,begin,pageSize);
    }
}
