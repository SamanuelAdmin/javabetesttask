package com.samanuel.auth_api.entity;

import lombok.Data;
import lombok.AllArgsConstructor;


@Data @AllArgsConstructor public class AuthEntity {
  private String email;
  private String password;
}
