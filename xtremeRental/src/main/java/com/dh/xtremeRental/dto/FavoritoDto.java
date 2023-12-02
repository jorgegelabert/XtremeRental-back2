package com.dh.xtremeRental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoDto {
    private Integer id;
    private ProductoDto producto;
    private UserDto usuario;
}
