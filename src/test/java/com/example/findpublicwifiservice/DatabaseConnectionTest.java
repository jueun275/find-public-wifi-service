package com.example.findpublicwifiservice;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class DatabaseConnectionTest {
    private DatabaseConnection dbConnection;

    @BeforeEach
    public void setUp() {
        dbConnection = DatabaseConnection.getInstance();
    }

    @Test
    public void testConnect_Success() throws SQLException, ClassNotFoundException {
        // 데이터베이스 연결 시도
        dbConnection.getConnection();
        // 연결 상태 확인
        if (dbConnection.isConnected()) {
            System.out.println("연결이 성공적으로 이루어졌습니다: " + dbConnection.getConnection());
        }

        // 실제 연결된 Connection 객체가 유효한지 확인
        Connection conn = dbConnection.getConnection();
        assertFalse(conn.isClosed(), "Connection should be open");
    }


    @Test
    public void testDisconnect() throws SQLException, ClassNotFoundException {

        // 연결 상태 확인
        dbConnection.getConnection();
        if (dbConnection.isConnected()) {
            System.out.println("연결이 성공적으로 이루어졌습니다: " + dbConnection.getConnection());
        }

        assertTrue(dbConnection.isConnected(), "Connection should be established");

        // 연결 종료
        dbConnection.disconnect();

        // 연결 종료 후 상태 확인
        if (!dbConnection.isConnected()) {
            System.out.println("DB가 성공적으로 닫혔습니다 ");
        }
        assertFalse(dbConnection.isConnected(), "Connection should be closed after disconnect");
    }
}
