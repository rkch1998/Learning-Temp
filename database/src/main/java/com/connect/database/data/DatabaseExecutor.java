package com.connect.database.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DatabaseExecutor {

    private final DataSource dataSource;

    public DatabaseExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String executeQuery(String schemaName, String functionName) {
        String query = String.format("""
                SELECT F.full_text :: varchar
                FROM
                (
                SELECT oid, proname
                FROM pg_proc
                WHERE proname like '%s'
                    AND pronamespace IN (SELECT oid FROM pg_namespace WHERE nspname = '%s')
                ) AS P
                JOIN LATERAL
                (SELECT pg_get_functiondef(P.oid) AS full_text) AS F ON TRUE;""", functionName, schemaName);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("full_text")+";";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
