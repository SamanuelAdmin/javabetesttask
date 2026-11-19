package com.samanuel.auth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samanuel.auth_api.model.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {
  Session findByTokenAddress(String token);
}
