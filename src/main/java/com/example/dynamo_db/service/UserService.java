package com.example.dynamo_db.service;
import com.example.dynamo_db.model.User;
import com.example.dynamo_db.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> editUser(String id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            // Update the fields you want to allow editing
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setAge(updatedUser.getAge());

            return userRepository.save(existingUser);
        });
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}

