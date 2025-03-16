package com.example.findpublicwifiservice.controller;

import com.example.findpublicwifiservice.dto.WiFiDto;
import com.example.findpublicwifiservice.service.BookmarkGroupService;
import com.example.findpublicwifiservice.service.BookmarkService;
import com.example.findpublicwifiservice.service.HistoryService;
import com.example.findpublicwifiservice.service.WiFiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/wifi"})
public class WiFiServlet extends HttpServlet {

    private WiFiService wifiService;
    private HistoryService historyService;
    private BookmarkGroupService bookmarkGroupService;

    @Override
    public void init() throws ServletException {
        super.init();
        wifiService = new WiFiService();
        historyService = new HistoryService();
        bookmarkGroupService =  new BookmarkGroupService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("getWiFiList".equals(action)) {
                List<WiFiDto> responseDto = getWiFiInfoList(request.getParameter("lat"), request.getParameter("lnt"));
                request.setAttribute("wifiList", responseDto);
                HttpSession session = request.getSession();
                session.setAttribute("wifiList", responseDto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } else if ("getWiFiDetail".equals(action)) {
                WiFiDto wifiInfo = getWiFiInfo(request.getParameter("mgrNo"));
                request.setAttribute("wifi", wifiInfo);
                request.setAttribute("bookmarkGroupList", bookmarkGroupService.getAllBookMarkGroups());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                request.setAttribute("errorMessage", "잘못된 요청입니다");
                dispatcher.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "WiFi 정보를 가져오는 중에 문제가 발생했습니다.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }

    }

    private List<WiFiDto> getWiFiInfoList(String lat, String lnt) throws SQLException, ClassNotFoundException {
        historyService.save(lat, lnt);
        return wifiService.getWiFiInfoList(lat,lnt);
    }

    private WiFiDto getWiFiInfo(String id) throws SQLException, ClassNotFoundException {
        return wifiService.getWiFiDetail(id);
    }

}
