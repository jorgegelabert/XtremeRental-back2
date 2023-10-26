package com.dh.xtremeRental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

@Entity
@Table
public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String nombreProducto;
        private String descripcionProducto;
        private Integer stock;
        private Double precioPorHora;
        private String imagen;

        @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
        @JsonIgnore
        //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private Set<Alquiler> alquileres; //un producto al tener stock puede tener varios alquileres
}
