package com.vaneza.users.utils.mapper;

import com.vaneza.users.model.dto.UserDto;
import com.vaneza.users.model.dto.UserLoginDto;
import com.vaneza.users.model.entity.Role;
import com.vaneza.users.model.entity.User;

import java.util.Collections;
import java.util.List;

public class Mappers {

  private Mappers() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static UserDto userToUserDto(User user, List<Role> roles) {
    return UserDto.builder()
        .idUser(user.getIdUser())
        .username(user.getUsername())
        .name(user.getName())
        .lastname(user.getLastname())
        .dni(user.getDni())
        .actualRegistered(user.getActualRegistered())
        .roles(roles != null ? roles : Collections.emptyList())
        .build();
  }

  public static UserLoginDto userToUserLoginDto(User user, List<Role> roles) {
    return UserLoginDto.builder()
        .idUser(user.getIdUser())
        .username(user.getUsername())
        .password(user.getPassword())
        .name(user.getName())
        .lastname(user.getLastname())
        .dni(user.getDni())
        .actualRegistered(user.getActualRegistered())
        .roles(roles != null ? roles : Collections.emptyList())
        .build();
  }
}
