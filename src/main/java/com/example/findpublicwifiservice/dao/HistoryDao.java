package com.example.findpublicwifiservice.dao;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dto.HistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HistoryDao {

    private static final Logger logger = Logger.getLogger(HistoryDao.class.getName());
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void insert(double x, double y) {
        logger.info("insert history");
        String sql = "INSERT INTO HISTORY (LAT, LNT, CREATE_DT) VALUES (?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        try (Connection connection = databaseConnection.getConnection();
        ) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, x);
            ps.setDouble(2, y);
            ps.setString(3, formattedDate);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void delete(int id) {
        String sql = "DELETE FROM HISTORY WHERE ID = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HistoryDto> getHistoryInfo() {
        List<HistoryDto> historyList = new ArrayList<>();
        String sql = "SELECT ID, LAT, LNT, CREATE_DT FROM HISTORY";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                historyList.add(HistoryDto.fromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyList;
    }
}