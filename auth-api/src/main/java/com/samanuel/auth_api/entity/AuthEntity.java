package com.samanuel.auth_api.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
public class AuthEntity {
  private String email;
  private String password;
}
