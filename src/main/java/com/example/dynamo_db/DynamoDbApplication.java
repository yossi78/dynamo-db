package com.example.dynamo_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class DynamoDbApplication {
	public static void main(String[] args) {
		SpringApplication.run(DynamoDbApplication.class, args);
	}
}

