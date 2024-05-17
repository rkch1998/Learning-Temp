package com.connect.DB;

import com.connect.DB.service.DataBaseConnect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class DbApplication {


    public static void main(String[] args) {

		try {
			Connection connection = DataBaseConnect.getConnection();

			PreparedStatement statement = connection.prepareStatement("SELECT 1");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
			}
			resultSet.close();
			statement.close();
			DataBaseConnect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
