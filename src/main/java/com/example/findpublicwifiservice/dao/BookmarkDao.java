package com.example.findpublicwifiservice.dao;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dto.BookmarkDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookmarkDao {
    private static final Logger logger = Logger.getLogger(BookmarkDao.class.getName());
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void insert(String xSwifiMgrNo, int bookmarkGroupId) {
        String sql = "INSERT INTO BOOK_MARK (X_SWIFI_MGR_NO, BOOK_MARK_GROUP_ID, CREATE_DT) VALUES (?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // 파라미터 설정
            ps.setString(1, xSwifiMgrNo);
            ps.setInt(2, bookmarkGroupId);
            ps.setString(3, formattedDate);

            // 실행
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 북마크 삭제
    public void delete(int id) {
        String sql = "DELETE FROM BOOK_MARK WHERE ID = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookmarkDto> selectAll() {
        List<BookmarkDto> bookmarkList = new ArrayList<>();
        String sql = "SELECT bm.ID, bm.X_SWIFI_MGR_NO, bm.BOOK_MARK_GROUP_ID, bm.CREATE_DT, " +
            "w.X_SWIFI_MAIN_NM, bg.NAME AS BOOK_MARK_GROUP_NAME " +
            "FROM BOOK_MARK bm " +
            "JOIN WIFI_INFO w ON bm.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO " +
            "JOIN BOOK_MARK_GROUP bg ON bm.BOOK_MARK_GROUP_ID = bg.ID " +
            "ORDER BY bg.ORDER_VALUE";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookmarkDto bookmarkDto = BookmarkDto.fromResultSet(rs);
                bookmarkList.add(bookmarkDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookmarkList;
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
