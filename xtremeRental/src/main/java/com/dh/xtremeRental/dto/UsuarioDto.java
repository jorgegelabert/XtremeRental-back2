package com.dh.xtremeRental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDto {

    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private String contrasena;
    private Boolean esAdmin;
}
