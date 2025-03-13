package com.example.findpublicwifiservice.dao;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.model.WiFiModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WiFiDao {
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();


    // WiFiModel 객체를 받아서 데이터베이스에 삽입하는 메서드
    // WiFiModel 리스트를 DB에 저장하는 메서드
    public void insert(List<WiFiModel> wifiList) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO WIFI VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.connect();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // 데이터 삽입
            for (WiFiModel wifi : wifiList) {
                statement.setString(1, wifi.getMgrNo());
                statement.setString(2, wifi.getDistrict());
                statement.setString(3, wifi.getWifiName());
                statement.setString(4, wifi.getAddress1());
                statement.setString(5, wifi.getAddress2());
                statement.setString(6, wifi.getInstallFloor());
                statement.setString(7, wifi.getInstallType());
                statement.setString(8, wifi.getInstallAgency());
                statement.setString(9, wifi.getServiceType());
                statement.setString(10, wifi.getConnectionType());
                statement.setInt(11, wifi.getInstallYear());
                statement.setString(12, wifi.getIndoorOutdoor());
                statement.setString(13, wifi.getRemarks());
                statement.setDouble(14, wifi.getLatitude());
                statement.setDouble(15, wifi.getLongitude());
                statement.setString(16, wifi.getWorkDatetime());

                statement.addBatch(); // 배치에 추가
            }

            // 배치 실행
            int[] rowsAffected = statement.executeBatch();
            System.out.println("Rows inserted: " + rowsAffected.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
