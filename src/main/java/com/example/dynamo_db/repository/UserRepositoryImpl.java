package com.example.dynamo_db.repository;
import com.example.dynamo_db.model.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DynamoDbTable<User> userTable;

    public UserRepositoryImpl(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(User.class));
    }

    @Override
    public User save(User user) {
        userTable.putItem(user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userTable.getItem(r -> r.key(k -> k.partitionValue(id))));
    }

    @Override
    public List<User> findAll() {
        return userTable.scan().items().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        userTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
