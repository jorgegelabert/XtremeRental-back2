package com.dh.xtremeRental.dto;

import com.dh.xtremeRental.entity.Imagen;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoDto {

    private Integer id;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer stock;
    private Double precioPorHora;
    private Imagen imagen;
}
