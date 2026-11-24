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
import org.springframework.dao.DataIntegrityViolationException;


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
    try {
      authService.register(authEntity);

      return new ResponseContract(201, "OK");
    } catch (DataIntegrityViolationException e) {
      System.err.println("Error while adding new user: \n");
      System.err.println(e);

      return new ResponseContract(400, "This email is already used.");
    }
  }

  @PostMapping("/login")
  public ResponseContract login(@RequestBody AuthEntity authEntity) {
    String token = new String();

    try {
      token = authService.login(authEntity);
    } catch (Exception e) {
      System.err.println("Error while login in: \n");
      System.err.println(e);

      return new ResponseContract(
        403, "Incorrect"
      );
    } 

    return new ResponseContract(
      200, token
    );
  }
}
