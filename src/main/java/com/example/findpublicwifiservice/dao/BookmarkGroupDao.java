package com.example.findpublicwifiservice.dao;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dto.BookmarkGroupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookmarkGroupDao {
    private static final Logger logger = Logger.getLogger(BookmarkDao.class.getName());
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    // 북마크 추가
    public void insert(String name, int orderValue) {
        logger.info("insert bookmark");

        String sql = "INSERT INTO BOOK_MARK_GROUP (NAME, ORDER_VALUE, CREATE_DT) VALUES (?, ?, ?)";

        String currentDateTime = getCurrentDateTime();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, orderValue);
            ps.setString(3, currentDateTime);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 북마크 삭제
    public void delete(int id) {
        logger.info("delete bookmark with id: " + id);

        String sql = "DELETE FROM BOOK_MARK_GROUP WHERE ID = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 북마크 목록 조회 (ORDER_VALUE로 정렬)
    public List<BookmarkGroupDto> selectAll() {
        List<BookmarkGroupDto> bookmarkGroupDtoList = new ArrayList<>();
        String sql = "SELECT ID, NAME, CREATE_DT, UPDATE_DT, ORDER_VALUE FROM BOOK_MARK_GROUP ORDER BY ORDER_VALUE ASC";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bookmarkGroupDtoList.add(BookmarkGroupDto.fromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookmarkGroupDtoList;
    }

    // 특정 북마크 조회
    public BookmarkGroupDto select(int id) {
        String sql = "SELECT ID, NAME, CREATE_DT, UPDATE_DT, ORDER_VALUE FROM BOOK_MARK_GROUP WHERE ID = ?";
        BookmarkGroupDto bookMarkGroupDto = null;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bookMarkGroupDto = BookmarkGroupDto.fromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookMarkGroupDto;
    }

    public void update(int id, String name, int orderValue) {
        logger.info("update bookmark with id: " + id);

        String sql = "UPDATE BOOK_MARK_GROUP SET NAME = ?, ORDER_VALUE = ?, UPDATE_DT = ? WHERE ID = ?";

        String currentDateTime = getCurrentDateTime();  // 현재 시간 가져오기

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, orderValue);
            ps.setString(3, currentDateTime);
            ps.setInt(4, id);  // ID 설정
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
