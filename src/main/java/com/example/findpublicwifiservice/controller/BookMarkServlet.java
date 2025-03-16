package com.example.findpublicwifiservice.controller;

import com.example.findpublicwifiservice.dto.BookmarkDto;
import com.example.findpublicwifiservice.service.BookmarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookmark")
public class BookMarkServlet extends HttpServlet {

    private BookmarkService bookmarkService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookmarkService = new BookmarkService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookmarkList", getBookmarkList());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addBookmark(request, response);
        } else if ("delete".equals(action)) {
            deleteBookmark(request, response);
        }
    }

    private void addBookmark(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 파라미터 받기
        String mgrNo = request.getParameter("mgrNo");
        int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroup"));
        bookmarkService.addBookmark(mgrNo, bookmarkGroupId);
        request.setAttribute("bookmarkList", getBookmarkList());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteBookmark(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));

        bookmarkService.deleteBookmark(id);
        request.setAttribute("bookmarkList", getBookmarkList());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark.jsp");
        dispatcher.forward(request, response);
    }

    private  List<BookmarkDto> getBookmarkList() {
        return bookmarkService.getAllBookmarks();
    }

}
