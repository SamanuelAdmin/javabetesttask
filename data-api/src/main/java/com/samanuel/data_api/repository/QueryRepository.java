package com.samanuel.data_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samanuel.data_api.model.Query;

public interface QueryRepository extends JpaRepository<Query, Integer> {
}
