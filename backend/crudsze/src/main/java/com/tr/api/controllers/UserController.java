package com.tr.api.controllers;

import com.tr.api.responses.UserResponse;
import com.tr.domain.dto.UserRequestDTO;
import com.tr.domain.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

  @Autowired private UserService service;

  @GetMapping
  public ResponseEntity<List<UserResponse>> findAllUsers() {

    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{stateId}")
  public ResponseEntity<UserResponse> findByIdUsers(@PathVariable Long userId) {

    return ResponseEntity.ok(service.findById(userId));
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody UserRequestDTO dto) {
    UserResponse userResponseDto = service.save(dto);
    return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserResponse> createUser(
      @PathVariable Long userId, @RequestBody UserRequestDTO dto) {
    UserResponse userResponseDto = service.update(userId, dto);
    return ResponseEntity.ok(userResponseDto);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
    service.delete(userId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
