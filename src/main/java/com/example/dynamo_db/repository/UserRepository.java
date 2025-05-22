package com.example.dynamo_db.repository;
import com.example.dynamo_db.model.User;
import java.util.List;
import java.util.Optional;



public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    List<User> findAll();
    void deleteById(String id);
}
