package org.example.service;

import org.example.entity.UserEntity;
import org.example.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity activateUser(int id){
        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

        UserEntity updated = new UserEntity(existing.getId(), existing.getEmail(), true);
        return userRepository.save(updated);
    }
}
