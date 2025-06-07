package com.vaneza.users.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {

  @Id
  @Column("id_user")
  private Long idUser;
  private String username;
  private String password;
  private String name;

  @Column("last_name")
  private String lastname;
  private String dni;

  @Column("actual_registered")
  private Boolean actualRegistered;
}
