package com.connect.database;

import com.connect.database.data.FunctionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseApplication implements CommandLineRunner {


	@Autowired
	private FunctionProcessor functionProcessor;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length != 2) {
			System.out.println("Usage: java -jar <jar-file> <input-file> <output-file>");
			System.exit(1);
		}

		String inputFilePath = "D:\\Learn\\GitHub\\function.txt";
		String outputFilePath = "D:\\Learn\\GitHub\\result.txt";
		functionProcessor.processFunctionsFromFile(inputFilePath, outputFilePath);
	}
}
