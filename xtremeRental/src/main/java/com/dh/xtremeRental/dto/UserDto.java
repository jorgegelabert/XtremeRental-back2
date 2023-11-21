package com.dh.xtremeRental.dto;

import com.dh.xtremeRental.User.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
