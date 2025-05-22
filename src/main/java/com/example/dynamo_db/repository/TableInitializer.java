package com.example.dynamo_db.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;

@Component
public class TableInitializer {

    private final DynamoDbClient dynamoDbClient;

    public TableInitializer(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @PostConstruct
    public void createTableIfNotExists() {
        String tableName = "Users";

        ListTablesResponse listTables = dynamoDbClient.listTables();
        if (!listTables.tableNames().contains(tableName)) {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName(tableName)
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("id")
                            .keyType(KeyType.HASH)
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("id")
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .provisionedThroughput(ProvisionedThroughput.builder()
                            .readCapacityUnits(5L)
                            .writeCapacityUnits(5L)
                            .build())
                    .build();

            dynamoDbClient.createTable(request);
            System.out.println("✅ Created table: " + tableName);
        } else {
            System.out.println("✅ Table already exists: " + tableName);
        }
    }
}
