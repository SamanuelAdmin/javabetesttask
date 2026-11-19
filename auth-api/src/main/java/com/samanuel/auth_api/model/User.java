package com.samanuel.auth_api.model;

import jakarta.persistence.*;
import com.samanuel.model.Session;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String hpass;

    // only  one active session for one user is acceptable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    private Session session;
}

