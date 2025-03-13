package com.example.findpublicwifiservice;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dao.WiFiDao;
import com.example.findpublicwifiservice.service.WiFiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WifiServiceITest {
    private WiFiService wifiService;
    private DatabaseConnection databaseConnection;
    private WiFiDao wiFiDao;

    @BeforeEach
    void setUp() throws Exception {
        // WiFiService를 실제 객체로 생성
        wifiService = new WiFiService();
        wiFiDao = new WiFiDao();
        databaseConnection = DatabaseConnection.getInstance();
    }

    @Test
    void testProcessAllWifiData() throws Exception {
        // 실제 데이터를 OpenAPI에서 가져와 처리하는 메서드를 호출
        databaseConnection.connect();
        wifiService.processAllWifiData();
    }

    private void resetDatabase() throws Exception {
        // DB를 초기화하는 작업 (기존 데이터를 모두 삭제)
        try (Connection connection = databaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            String deleteQuery = "DELETE FROM wifi";
            stmt.executeUpdate(deleteQuery);
            System.out.println("Database reset completed.");
        }
    }

}