package com.example.blogapp;

import com.example.blogapp.users.UserEntity;
import com.example.blogapp.users.UserRepository;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class JpaTestConfig {}
