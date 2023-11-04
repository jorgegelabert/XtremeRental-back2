package com.dh.xtremeRental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagenUrl;

    private String imagenPath;

 //   @ManyToOne(fetch=FetchType.LAZY)
 //   @JoinColumn(name="producto_id", nullable = false)
 //  @JsonIgnore
 //   private Producto producto;

}
