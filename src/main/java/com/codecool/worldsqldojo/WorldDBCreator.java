package com.codecool.worldsqldojo;

import java.sql.*;

public class WorldDBCreator {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/world";
    private static final String DB_USER = System.getenv("POSTGRES_DB_USER");
    private static final String DB_PASSWORD = System.getenv("POSTGRES_DB_PASSWORD");

    public PreparedStatement getPreparedStatement(String query) {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR: Connection error.");
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(String query) throws SQLException {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLTimeoutException e) {
            System.err.println("ERROR: SQL Timeout");
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();

        } catch (SQLTimeoutException e) {
            System.err.println("ERROR: SQL Timeout");
        }
        return null;
    }
}
