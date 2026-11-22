package com.samanuel.auth_api.service;

import com.samanuel.auth_api.entity.AuthEntity;
import com.samanuel.auth_api.model.User;


public interface AuthService {
  String login(AuthEntity authEntity) throws Exception;
  void register(AuthEntity authEntity);
  public User checkSession(String token) throws Exception;
}
