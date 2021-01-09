package com.book.web;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName BookServlet
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 22:38
 * @Version 1.0
 **/
public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), Book.class);
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        bookService.deleteById(i);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+ req.getParameter("pageNo"));
    }

    /**
     * 修改图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),Book.class);
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+ req.getParameter("pageNo"));
    }

    /**
     * 获取要修改的图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        Book book = bookService.queryBookById(i);
        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 获取所有图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryAllBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
