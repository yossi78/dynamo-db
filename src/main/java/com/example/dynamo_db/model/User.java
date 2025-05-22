package com.example.dynamo_db.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private int age;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
