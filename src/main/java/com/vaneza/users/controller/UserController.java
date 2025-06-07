package com.vaneza.users.controller;

import com.vaneza.users.model.dto.UserDto;
import com.vaneza.users.model.dto.UserLoginDto;
import com.vaneza.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public Flux<UserDto> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<UserDto> getUserById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @GetMapping("/username/{username}")
  public Mono<UserLoginDto> getUserByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }
}
