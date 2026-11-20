package com.samanuel.auth_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.samanuel.auth_api.entity.AuthEntity;
import com.samanuel.auth_api.entity.ResponseContract;
import com.samanuel.auth_api.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
  @Autowired
  private final AuthService authService;

  @GetMapping("/health")
  public ResponseContract health() {
    System.out.println("Health check OK");
    return new ResponseContract(200, "OK");
  }

  @PostMapping("/register")
  public ResponseContract register(@RequestBody AuthEntity authEntity) {
    authService.register(authEntity);

    System.out.println("Hello");
    return new ResponseContract(201, "OK");
  }

  @PostMapping("/login")
  public ResponseContract login(@RequestBody AuthEntity authEntity) {
    String token = new String();

    try {
      token = authService.login(authEntity);
    } catch (Exception e) {
      return new ResponseContract(
        403, "Incorrect"
      );
    } 

    return new ResponseContract(
      200, token
    );
  }
}
