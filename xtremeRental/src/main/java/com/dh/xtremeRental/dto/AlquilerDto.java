package com.dh.xtremeRental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AlquilerDto {

    private Integer id;
    private Double precioAlquilerProductoXHora;
    private ProductoDto producto;
    private UsuarioDto usuario;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaAltaAlquiler;
    private LocalDate fechaFinAlquiler;
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime horaAltaAlquiler;
    private LocalTime horaBajaAlquiler;
}
