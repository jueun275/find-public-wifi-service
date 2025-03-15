package com.example.findpublicwifiservice.controller;

import com.example.findpublicwifiservice.service.OpenApiService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenApiServlet extends HttpServlet {

    private OpenApiService wifiService;

    @Override
    public void init() throws ServletException {
        super.init();
        wifiService = new OpenApiService(getServletContext());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        wifiService.processAllWifiData();
    }
}
