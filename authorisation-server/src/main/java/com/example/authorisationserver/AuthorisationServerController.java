package com.example.authorisationserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthorisationServerController {

  @GetMapping("/auth")
  public ResponseEntity<String> sidecar(@RequestHeader(value = "Authorization") String authorization) {
    log.info("I was hit!!!");
    if (authorization.contains("tokenXXXXX")) {
      return new ResponseEntity<>("Unauthorized response from sidecar!", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("Hello from sidecar endpoint controller!", HttpStatus.OK);
  }
}
