package com.samanuel.data_api.controller;

import com.samanuel.data_api.contract.RequestContract;
import org.springframework.http.ResponseEntity;
import com.samanuel.data_api.contract.ResponseContract;
import com.samanuel.data_api.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainController {
  @Autowired private final MainService mainService;

  @PostMapping("/transform")
  public ResponseEntity<ResponseContract> transform(
      @RequestHeader("X-Internal-Token") String internalToken,
      @RequestBody RequestContract request
    ) {
      // base checking
      if (!mainService.checkToken(internalToken)) {
        return new ResponseEntity<ResponseContract>(
          new ResponseContract("Internal token failed."), 
          HttpStatusCode.valueOf(403)
        );
      }

      String processed = mainService.process(request.getText());

      return new ResponseEntity<ResponseContract>(
        new ResponseContract(processed), 
        HttpStatusCode.valueOf(200)
      );
      
    }
}
