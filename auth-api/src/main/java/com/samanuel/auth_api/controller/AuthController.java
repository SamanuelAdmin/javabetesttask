package com.samanuel.auth_api.controller;

import com.samanuel.auth_api.entity.AuthEntity;
import com.samanuel.auth_api.entity.ResponseContract;
import com.samanuel.auth_api.service.AuthService;

import lombok.AllArgsConstructor;


@Controller
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
  private final AuthService authService;

  @GetMapping("/register")
  public ResponseContract register(@RequestBody AuthEntity authEntity) {
    authService.register(authEntity);
  }

  @GetMapping("/login")
  public ResponseContract login(@RequestBody AuthEntity authEntity) {
    authService.login(authEntity);
  }

}
