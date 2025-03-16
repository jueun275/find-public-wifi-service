package com.example.findpublicwifiservice.controller;

import com.example.findpublicwifiservice.dto.HistoryDto;
import com.example.findpublicwifiservice.service.HistoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    private HistoryService historyService;

    public HistoryServlet() {
        historyService = new HistoryService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HistoryDto> responseDto = historyService.selectAll();
        request.setAttribute("historyList", responseDto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        historyService.delete(Integer.parseInt(request.getParameter("id")));
        List<HistoryDto> responseDto = historyService.selectAll();
        request.setAttribute("historyList", responseDto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history.jsp");
        dispatcher.forward(request, response);
    }

}
