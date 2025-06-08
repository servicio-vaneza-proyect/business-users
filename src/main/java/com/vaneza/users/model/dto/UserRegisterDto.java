package com.vaneza.users.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
  private String username;
  private String password;
  private String name;
  private String lastname;
  private String dni;
  private String role;
}
