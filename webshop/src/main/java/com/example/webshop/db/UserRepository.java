package com.example.webshop.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
