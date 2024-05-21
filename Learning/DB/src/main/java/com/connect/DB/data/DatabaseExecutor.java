package com.connect.DB.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExecutor implements DatabaseOperations{

    private final Connection connection;

    public DatabaseExecutor() throws SQLException{
        this.connection  = DataBaseConnect.getConnection();;
    }
    public DatabaseExecutor(String url, String userName, String password) throws SQLException{
        this.connection = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public String executeQuery(String schemaName, String functionName) {
        String query = String.format("""
                SELECT pg_get_functiondef(oid)
                FROM pg_proc
                WHERE proname = '%s'
                AND pronamespace = (SELECT oid FROM pg_namespace WHERE nspname = '%s')
                """, functionName, schemaName);

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return null;
    }
}
