package com.samanuel.auth_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "session")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // public data
    private String token;
}
