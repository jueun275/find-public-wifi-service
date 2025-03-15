package com.example.findpublicwifiservice.dao;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dto.WiFiDto;
import com.example.findpublicwifiservice.model.WiFiModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WiFiDao {
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void insert(List<WiFiModel> wifiList) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO WIFI_INFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // 데이터 삽입
            for (WiFiModel wifi : wifiList) {
                ps.setString(1, wifi.getMgrNo());
                ps.setString(2, wifi.getDistrict());
                ps.setString(3, wifi.getWifiName());
                ps.setString(4, wifi.getAddress1());
                ps.setString(5, wifi.getAddress2());
                ps.setString(6, wifi.getInstallFloor());
                ps.setString(7, wifi.getInstallType());
                ps.setString(8, wifi.getInstallAgency());
                ps.setString(9, wifi.getServiceType());
                ps.setString(10, wifi.getConnectionType());
                ps.setInt(11, wifi.getInstallYear());
                ps.setString(12, wifi.getIndoorOutdoor());
                ps.setString(13, wifi.getRemarks());
                ps.setDouble(14, wifi.getLatitude());
                ps.setDouble(15, wifi.getLongitude());
                ps.setString(16, wifi.getWorkDatetime());

                ps.addBatch(); // 배치에 추가
            }

            // 배치 실행
            int[] rowsAffected = ps.executeBatch();
            System.out.println("Rows inserted: " + rowsAffected.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WiFiDto> selectByDistance(double lat, double lng) throws SQLException, ClassNotFoundException {
        List<WiFiDto> wifiDtoList = new ArrayList<>();
        // SQL 쿼리
        String sql = "SELECT *, " +
            "(6371 * acos(" +
            "cos(radians(?)) * cos(radians(lat)) " +
            "* cos(radians(lnt) - radians(?)) " +
            "+ sin(radians(?)) * sin(radians(lat)) " +
            ")) AS distance " +
            "FROM WIFI_INFO " +
            "WHERE (6371 * acos(" +
            "cos(radians(?)) * cos(radians(lat)) " +
            "* cos(radians(lnt) - radians(?)) " +
            "+ sin(radians(?)) * sin(radians(lat)) " +
            ")) <= ? " +
            "ORDER BY distance ASC " +
            "LIMIT 20;";

        try (Connection connection = databaseConnection.getConnection();){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, lat);  // 위도
            ps.setDouble(2, lng); // 경도
            ps.setDouble(3, lat);  // 위도

            ps.setDouble(4, lat);  // 위도
            ps.setDouble(5, lng); // 경도
            ps.setDouble(6, lat);  // 위도
            ps.setDouble(7, 10);    // 반경
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WiFiDto wifiDto = WiFiDto.fromResultSet(rs);
                wifiDto.setDistance(rs.getDouble("DISTANCE"));
                wifiDtoList.add(wifiDto);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return wifiDtoList;
    }

    public WiFiDto select(String mgrNo) throws SQLException, ClassNotFoundException {
        WiFiDto wifiDto  = new WiFiDto();

        String sql = "SELECT * FROM WIFI_INFO WHERE X_SWIFI_MGR_NO = ?";

        try (Connection connection = databaseConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mgrNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                wifiDto = WiFiDto.fromResultSet(rs);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return wifiDto;
    }

}
