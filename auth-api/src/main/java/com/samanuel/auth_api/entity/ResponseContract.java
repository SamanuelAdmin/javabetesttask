package com.samanuel.auth_api.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data @AllArgsConstructor public class ResponseContract {
  private Integer status;
  private String message;
}
