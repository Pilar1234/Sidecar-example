package com.example.mainapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

  @GetMapping("/sampleTest")
  public ResponseEntity<String> sample() {
    return new ResponseEntity<>("Hello from main application!" ,HttpStatus.OK);
  }
}

