package com.book.test;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;


public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "咸鱼的一生", new BigDecimal(9999), "LuckyAzrael", 9999, 10, null));
    }

    @Test
    public void deleteById() {
        bookService.deleteById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "不再咸鱼", new BigDecimal(9999), "LuckyAzrael", 9999, 10, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryAllBooks() {
        for (Book queryBook : bookService.queryAllBooks()) {
            System.out.println("queryBook = " + queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, 4));
    }
}