package com.connect.DB;

import com.connect.DB.service.FunctionProcessor;
import com.connect.DB.util.FunctionWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.SQLException;
import com.connect.DB.data.DatabaseExecutor;
@SpringBootApplication
public class DbApplication {

	public static void main(String[] args) {
		try {
			// Initialize dependencies
//			DatabaseExecutor databaseExecutor = new DatabaseExecutor("jdbc:postgresql://localhost:5432/db_name", "username", "password");
			DatabaseExecutor databaseExecutor = new DatabaseExecutor();
			FunctionWriter fileWriter = new FunctionWriter();
			FunctionProcessor functionProcessor = new FunctionProcessor(databaseExecutor, fileWriter);

			// Example function to process
			String function = "tds.\"InsertDownloads\"";
			functionProcessor.processFunction(function);
		} catch (SQLException e) {
			System.out.println("Error connecting to database: " + e.getMessage());
		}
	}

/*

	public static String query(String schemaName, String functionName){
		String finalQuery =  String.format("""
				SELECT F.full_text :: varchar
				FROM
				(
				SELECT oid, proname
				FROM pg_proc
				WHERE proname like '%s'
					AND pronamespace IN (SELECT oid FROM pg_namespace WHERE nspname = '%s')
				) AS P
				JOIN LATERAL
				(SELECT pg_get_functiondef(P.oid) AS full_text) AS F ON TRUE;""", functionName ,schemaName);
		System.out.println(finalQuery);
		return finalQuery;
	}

	public static String[] splitFunction(String function){
		return function.split("\\.");
	}

    public static void main(String[] args) {

		String filePath = "D:\\Learn\\GitHub\\result.txt";

		try {

			Connection connection = DataBaseConnect.getConnection();

			String function = "tds.InsertDownloads";

			String[] result = splitFunction(function);
			String schema;
			String functionName;

			if(result != null && result.length == 2) {
				schema = result[0];
				functionName = result[1];

				PreparedStatement statement = connection.prepareStatement(query(schema, functionName));

				ResultSet resultSet = statement.executeQuery();
//				System.out.println(resultSet.next());
				while (resultSet.next()) {
					System.out.println("Written Successfully");
					writeIntoFile(resultSet, filePath, schema, functionName);

				}

			}else{
				System.out.println("Invalid function name format");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void writeIntoFile(ResultSet resultSet, String filePath, String schema, String functionName){
		try(BufferedWriter writer = new BufferedWriter((new FileWriter(filePath, false)))){
			//int id = resultSet.getInt("StudentId");
			String name = resultSet.getString("full_text");
			String dropFunction = String.format("DROP FUNCTION IF EXISTS %s.\"%s\";", schema, functionName);
			writer.write(dropFunction);
			writer.newLine();
			writer.write(name);
			writer.newLine();
			System.out.println(name);
			System.out.println(dropFunction);
		}catch(IOException e) {
			System.out.println("Error occurred while writing the file");
		}catch (SQLException e) {
			System.out.println("Error occurred while from getting the string");
		}
	}
*/
}
