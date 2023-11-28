package com.dh.xtremeRental.dto;

import com.dh.xtremeRental.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SubCategoriaDto {

    private Integer id;
    private String nombre;
    private Set<Producto> productos;
}
