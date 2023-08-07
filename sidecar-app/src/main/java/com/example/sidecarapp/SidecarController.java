package com.example.sidecarapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class SidecarController {

  @Autowired
  private RestTemplate restTemplate;
  @Value("${main.application.url}")
  private String mainAppUrl;

  @GetMapping("/sidecar")
  public ResponseEntity<String> sidecar() {

    log.info("Main App URL: {}", mainAppUrl);
    ResponseEntity<String> httpResponse = this.restTemplate.getForEntity(mainAppUrl + "/sampleTest", String.class);

    return new ResponseEntity<>("Hello from sidecar endpoint controller!" + " Main app responded with: " + httpResponse.getBody(), HttpStatus.OK);
  }
}
