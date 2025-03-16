package com.example.findpublicwifiservice.controller;

import com.example.findpublicwifiservice.dto.BookmarkGroupDto;
import com.example.findpublicwifiservice.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/bookmark-group")
public class BookmarkGroupServlet extends HttpServlet {

    private BookmarkGroupService bookmarkGroupService;
    private static final Logger logger = Logger.getLogger(BookmarkGroupServlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookmarkGroupService = new BookmarkGroupService();
    }

    // GET 요청: 북마크 그룹 목록 조회
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            // 북마크 그룹 목록 조회
            request.setAttribute("bookmarkGroupList", getList());
            request.getRequestDispatcher("bookmark-group.jsp").forward(request, response);
        } else if ("view".equals(action)) {
            // 특정 북마크 그룹 조회
            String id = request.getParameter("id");
            BookmarkGroupDto BookmarkGroup = bookmarkGroupService.getBookMarkGroup(id);
            request.setAttribute("BookmarkGroup", BookmarkGroup);
            request.getRequestDispatcher("book-mark-group-view.jsp").forward(request, response);
        }
    }

    // POST 요청: 북마크 그룹 추가 또는 삭제
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+action);
        if ("add".equals(action)) {
            // 북마크 그룹 추가
            logger.info("Add Bookmark Group");
            String name = request.getParameter("bookmarkName");
            String orderValue =request.getParameter("bookmarkOrder");
            bookmarkGroupService.addBookMarkGroup(name, orderValue);

            request.setAttribute("bookmarkGroupList", getList());
            request.getRequestDispatcher("bookmark-group.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // 북마크 그룹 삭제
            logger.info("Delete Bookmark Group");
            String id = request.getParameter("id");

            bookmarkGroupService.removeBookMarkGroup(id);

            request.setAttribute("bookmarkGroupList", getList());
            request.getRequestDispatcher("bookmark-group.jsp").forward(request, response);

        } else if ("update".equals(action)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String orderValue =request.getParameter("orderValue");
            bookmarkGroupService.updateBookMarkGroup(name, orderValue, id);

            request.setAttribute("bookmarkGroupList", getList());
            request.getRequestDispatcher("bookmark-group.jsp").forward(request, response);
        }
    }

    private  List<BookmarkGroupDto> getList(){
        return bookmarkGroupService.getAllBookMarkGroups();
    }


}
