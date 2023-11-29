package com.dh.xtremeRental.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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
        private Double precioPorHora; //Por día
        private String categoria;

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name="producto_id", referencedColumnName ="id" )
        private List<Imagen> imagenes;

        @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private Set<Alquiler> alquileres;


       @ManyToMany
       @JoinTable(
               name = "producto_subcategoria",
               joinColumns = @JoinColumn(name = "producto_id"),
               inverseJoinColumns = @JoinColumn(name = "subcategoria_id")
       )
       private Set<SubCategoria> subcategorias = new HashSet<>();
}