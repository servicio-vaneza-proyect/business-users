package com.vaneza.users.service;

import com.vaneza.users.model.dto.UserDto;
import com.vaneza.users.model.dto.UserLoginDto;
import com.vaneza.users.model.dto.UserRegisterDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
  Flux<UserDto> findAll();
  Mono<UserDto> findById(Long id);
  Mono<UserLoginDto> findByUsername(String username);
  Mono<UserDto> register(UserRegisterDto userRegisterDto);
}
