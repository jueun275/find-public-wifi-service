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

    // 싱글톤 인스턴스를 반환하는 메서드
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // 데이터베이스 연결을 시작하는 메서드
    public void connect() throws SQLException, ClassNotFoundException {
        if (!isConnected()) {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to database.");
        }
    }

    // 데이터베이스 연결을 종료하는 메서드
    public void disconnect() {
        close(connection);
    }

    // JDBC 자원을 안전하게 종료하는 메서드
    private void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                System.err.println("Error while closing resource: " + e.getMessage());
            }
        }
    }

    // Connection 반환 메서드
    public Connection getConnection() {
        return this.connection;
    }

    // 연결 상태 확인 메서드
    public boolean isConnected() {
        try {
            return this.connection != null && !this.connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Error checking connection status: " + e.getMessage());
            return false;
        }
    }

    // PreparedStatement와 ResultSet을 안전하게 닫는 메서드
    public void closeStatement(PreparedStatement ps) {
        close(ps);
    }

    public void closeResultSet(ResultSet rs) {
        close(rs);
    }
}
