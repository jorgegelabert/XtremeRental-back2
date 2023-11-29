package com.dh.xtremeRental.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "subcategoria")
public class SubCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

}