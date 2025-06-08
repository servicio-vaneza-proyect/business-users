package com.vaneza.users.controller;

import com.vaneza.users.model.dto.UserDto;
import com.vaneza.users.model.dto.UserLoginDto;
import com.vaneza.users.model.dto.UserRegisterDto;
import com.vaneza.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<UserDto> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<UserDto> getUserById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @GetMapping("/username/{username}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<UserLoginDto> getUserByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<UserDto> saveUser(@RequestBody UserRegisterDto userCreateDto) {
    return userService.register(userCreateDto);
  }
}
