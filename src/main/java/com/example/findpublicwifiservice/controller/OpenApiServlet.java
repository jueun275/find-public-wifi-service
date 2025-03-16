package com.example.findpublicwifiservice.controller;

import com.example.findpublicwifiservice.service.OpenApiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/load-wifi")
public class OpenApiServlet extends HttpServlet {

    private OpenApiService wifiService;

    @Override
    public void init() throws ServletException {
        super.init();
        wifiService = new OpenApiService(getServletContext());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int count =  wifiService.processAllWifiData();
        request.setAttribute("count", count);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/load-wifi.jsp");
    }
}
