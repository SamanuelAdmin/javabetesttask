package com.samanuel.data_api.controller;

import com.samanuel.data_api.contract.RequestContract;
import com.samanuel.data_api.contract.ResponseContract;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainController {
  @Autowired private final MainService mainService;

  @PostMapping("/transform")
  public ResponseContract transform(
      @RequestHeader("X-Internal-Token") String internalToken,
      @RequestBody RequestContract request
    ) {
      // base checking
      if (!mainService.checkToken(internalToken)) {
        return new ResponseEntity<ResponseContract>(
          new ResponseContract("Internal token failed."), 403
        );
      }

      String processed = mainService.process(request.getTest());

      return new ResponseEntity<RequestContract>(
        new ResponseContract(processed). 200
      );
      
    }
}
