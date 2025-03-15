package com.example.findpublicwifiservice.config;

import java.sql.*;

// spring 에서는 DB 커넥션풀로 관리하는데 서블릿에서 서정방방법을 몰라서  싱글톤으로 관리합니다.
public class DatabaseConnection {

    private static final String DB_URL = "jdbc:sqlite:db/wifi_db.sqlite";
    private Connection connection;

    // 싱글톤 인스턴스
    private static DatabaseConnection instance;

    // 생성자를 private으로 설정하여 외부에서 인스턴스 생성을 방지
    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                // SQLite JDBC 드라이버 로드
                Class.forName("org.sqlite.JDBC");
                // 연결 설정
                this.connection = DriverManager.getConnection(DB_URL);
                System.out.println("DB에 연결되었습니다");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("DB연결에 문제가 생겼습니다. " + e.getMessage());
                throw new SQLException("Error connecting to database", e);
            }
        }
        return this.connection;
    }

    public void disconnect() {
        close(this.connection);
    }

    private void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                System.err.println("Error while closing resource: " + e.getMessage());
            }
        }
    }


    // 연결 상태 확인 메서드 (선택적으로 사용)
    public boolean isConnected() {
        try {
            return this.connection != null && !this.connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Error checking connection status: " + e.getMessage());
            return false;
        }
    }
}
