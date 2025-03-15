package com.example.findpublicwifiservice.service;

import com.example.findpublicwifiservice.dao.WiFiDao;
import com.example.findpublicwifiservice.dto.WiFiDto;

import java.sql.SQLException;
import java.util.List;

public class WiFiService {

    private WiFiDao wifiDao;

    public WiFiService() {
        wifiDao = new WiFiDao();
    }

    public List<WiFiDto> getWiFiInfoList(String lat, String lnt) throws SQLException, ClassNotFoundException {
        double x = Double.parseDouble(lat);
        double y = Double.parseDouble(lnt);
        return wifiDao.selectByDistance(x, y);
    }

    public WiFiDto getWiFiDetail(String id) throws SQLException, ClassNotFoundException {
       return wifiDao.select(id);
    }
}
