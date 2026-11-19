package com.samanuel.auth_api.service;

import com.samanuel.auth_api.entity.AuthEntity;


public interface AuthService {
  String login(AuthEntity authEntity);
  void register(AuthEntity authEntity);
}
