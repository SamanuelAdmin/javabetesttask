package com.samanuel.data_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samanuel.data_api.model.Query;

public interface SessionRepository extends JpaRepository<Query, Integer> {
}
