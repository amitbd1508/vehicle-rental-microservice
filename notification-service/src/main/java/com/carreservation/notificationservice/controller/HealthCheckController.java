package com.carreservation.notificationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HealthCheckController {
  @GetMapping
  public ResponseEntity<String> getHealth() {
    return ResponseEntity.ok("Alive");

  }
}
