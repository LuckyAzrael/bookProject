package com.book.test;

import com.book.bean.Book;
import com.book.dao.BookDAO;
import com.book.dao.impl.BookDAOIpml;
import com.book.utils.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class BookDAOTest {
    private BookDAO bookDAO = new BookDAOIpml();
    @Test
    public void addBook() {
        int i = bookDAO.addBook( new Book(null, "咸鱼的一生", new BigDecimal(9999), "LuckyAzrael", 9999, 10, null));
        System.out.println("i = " + i);
    }

    @Test
    public void deleteById() {

        int i = bookDAO.deleteById(21);

        System.out.println("i = " + i);
    }

    @Test
    public void updateBook() {
        int i = bookDAO.updateBook(new Book(7, "咸鱼的一生", new BigDecimal(9999), "LuckyAzrael", 9999, 10, null));
        System.out.println("i = " + i);
    }

    @Test
    public void queryBookById() {
        Book book = bookDAO.queryBookById(7);
        System.out.println("book = " + book);
    }

    @Test
    public void queryAllBooks() {

        for(Book queryBook : bookDAO.queryAllBooks()){
            System.out.println(queryBook);
        }


    }
    @Test
    public void queryForPageTotalCount(){
        Integer integer = bookDAO.queryForPageTotalCount();
        System.out.println("integer = " + integer);
    }


    @Test
    public void queryForPageItems(){
        for(Book queryBook : bookDAO.queryForPageItems(0,4)){
            System.out.println(queryBook);
        }
    }
    @Test
    public void queryForPageItemsByPrice() {
        for(Book queryBook : bookDAO.queryForPageItemsByPrice(0,4,0,50)){
            System.out.println(queryBook);
        }
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        Integer integer = bookDAO.queryForPageTotalCountByPrice(0,50);
        System.out.println("integer = " + integer);
    }
}