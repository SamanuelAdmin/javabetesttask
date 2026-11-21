package com.samanuel.auth_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.samanuel.auth_api.entity.AuthEntity;
import com.samanuel.auth_api.entity.ProcessEntity;
import com.samanuel.auth_api.entity.ProcessContract;
import com.samanuel.auth_api.entity.ResponseContract;
import com.samanuel.auth_api.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;


@RestController
@RequestMapping("/api/process")
@AllArgsConstructor
public class ProcessController {
  @Autowired private final AuthService authService;
  @Autowired private final ProcessService processService;

  @GetMapping("/health")
  public ResponseContract health() {
    System.out.println("Health check OK");
    return new ResponseContract(200, "OK");
  }

  @PostMapping("/")
  public register(
      @RequestHeader("Authorization") String token,
      @RequestBody ProcessEntity processEntity
  ) {
    if (token.split(" ")[0] != "Bearer") {
      return ResponseContract(401, "Authorization failed.");
    }

    // check and get user
    User user = authEntity.checkSession(token.split(" ")[1]);

    try {
      String processedText = processService.process(user, processEntity);

      return 
    } catch (Exception e) {
      System.err.println("Error while processing test: ");
      System.err.println(e);

      return ResponseContract(500, "Error");
    }
  }
}
