package com.samanuel.auth_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "session")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // FROM https://www.baeldung.com/hibernate-creationtimestamp-updatetimestamp
    @CreationTimestamp
    private Date creationTime;

    private Integer expiration;

    // public data
    private String token;

    // FROM https://www.javathinking.com/blog/how-can-you-make-a-created-at-column-generate-the-creation-date-time-automatically-like-an-id-automatically-gets-created/
    @PrePersist  
    protected void onCreate() {}

    public long getExpirationTime() {
        return this.creationTime.getTime() + this.expiration;
    }
}
