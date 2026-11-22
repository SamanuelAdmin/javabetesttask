package com.samanuel.auth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samanuel.auth_api.model.User;
import com.samanuel.auth_api.model.Session;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email);
  User findBySession(Session session);
}
