package com.vaneza.users.service.impl;

import com.vaneza.users.model.dto.UserDto;
import com.vaneza.users.model.dto.UserLoginDto;
import com.vaneza.users.model.dto.UserRegisterDto;
import com.vaneza.users.model.entity.User;
import com.vaneza.users.model.entity.UserRole;
import com.vaneza.users.repository.RoleRepository;
import com.vaneza.users.repository.UserRepository;
import com.vaneza.users.repository.UserRoleRepository;
import com.vaneza.users.service.UserService;
import com.vaneza.users.utils.mapper.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

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

  @Override
  @Transactional
  public Mono<UserDto> register(UserRegisterDto userRegisterDto) {
    User user = User.builder()
        .username(userRegisterDto.getUsername())
        .name(userRegisterDto.getName())
        .lastname(userRegisterDto.getLastname())
        .dni(userRegisterDto.getDni())
        .password(passwordEncoder.encode(userRegisterDto.getPassword()))
        .actualRegistered(true)
        .build();

    return userRepository.save(user)
        .flatMap(savedUser -> roleRepository.findByName(userRegisterDto.getRole())
            .flatMap(role -> userRoleRepository.save(new UserRole(role.getIdRole(), savedUser.getIdUser()))
                .thenReturn(Mappers.userToUserDto(savedUser, List.of(role)))));
  }
}
