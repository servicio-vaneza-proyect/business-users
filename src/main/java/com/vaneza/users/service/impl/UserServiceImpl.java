package com.vaneza.users.service.impl;

import com.vaneza.users.model.dto.UserDto;
import com.vaneza.users.model.dto.UserLoginDto;
import com.vaneza.users.repository.RoleRepository;
import com.vaneza.users.repository.UserRepository;
import com.vaneza.users.repository.UserRoleRepository;
import com.vaneza.users.service.UserService;
import com.vaneza.users.utils.mapper.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final RoleRepository roleRepository;

  @Override
  public Flux<UserDto> findAll() {
    return userRepository.findAll()
        .flatMap(user -> userRoleRepository.findAllByIdUser(user.getIdUser())
            .flatMap(userRole -> roleRepository.findById(userRole.getIdRole()))
            .collectList()
            .map(roles -> Mappers.userToUserDto(user, roles)));
  }

  @Override
  public Mono<UserDto> findById(Long id) {
    return userRepository.findById(id)
        .flatMap(user -> userRoleRepository.findAllByIdUser(user.getIdUser())
            .flatMap(userRole -> roleRepository.findById(userRole.getIdRole()))
            .collectList()
            .map(roles -> Mappers.userToUserDto(user, roles)));
  }

  @Override
  public Mono<UserLoginDto> findByUsername(String username) {
    return userRepository.findByUsername(username)
        .flatMap(user -> userRoleRepository.findAllByIdUser(user.getIdUser())
            .flatMap(userRole -> roleRepository.findById(userRole.getIdRole()))
            .collectList()
            .map(roles -> Mappers.userToUserLoginDto(user, roles)));
  }
}
