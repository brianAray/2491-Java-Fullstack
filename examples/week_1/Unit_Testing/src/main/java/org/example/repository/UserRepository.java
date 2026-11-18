package org.example.repository;

import org.example.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(int id);
    UserEntity save(UserEntity user);
}
