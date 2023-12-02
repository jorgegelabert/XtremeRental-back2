package com.dh.xtremeRental.dto;

import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.entity.Favorito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private String password;
    private Role role;
    private Set<Favorito> favoritos;


}
