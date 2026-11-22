package com.samanuel.auth_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.samanuel.auth_api.entity.AuthEntity;
import com.samanuel.auth_api.entity.ProcessEntity;
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
import com.samanuel.auth_api.service.ProcessService;
import com.samanuel.auth_api.model.User;


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

  @PostMapping("")
  public ResponseContract process(
      @RequestHeader("Authorization") String token,
      @RequestBody ProcessEntity processEntity
  ) {
    if (!token.split(" ")[0].equals("Bearer")) {
      System.out.println(
          String.format(
            "Authorization failed with token '%s' '%s'",
            token.split(" ")[0], token.split(" ")[1]
          )
        );
      return new ResponseContract(401, "Authorization failed.");
    }

    // check and get user
    User user = this.authService.checkSession(token.split(" ")[1]);
    
    if (user == null) {
      System.out.println("Authorization failed, session is incorrect".concat(token.split(" ")[1]));
      return new ResponseContract(401, "Incorrect session.");
    }

    try {
      String processedText = processService.process(user, processEntity);

      return new ResponseContract(200, processedText);
    } catch (Exception e) {
      System.err.println("Error while processing test: ");
      System.err.println(e);

      return new ResponseContract(500, "Error");
    }
  }
}
