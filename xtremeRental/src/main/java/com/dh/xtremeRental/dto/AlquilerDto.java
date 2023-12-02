package com.dh.xtremeRental.dto;

import com.dh.xtremeRental.entity.Producto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AlquilerDto {

    private Integer id;
    private Double precioTotal;
    private Set<Producto> producto;
    private UserDto usuario;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaAltaAlquiler;
    private LocalDate fechaFinAlquiler;
}
