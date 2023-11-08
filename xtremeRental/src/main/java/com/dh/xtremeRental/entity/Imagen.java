package com.dh.xtremeRental.entity;

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

    private String url;

    private String keyPath;

 //   @ManyToOne(fetch=FetchType.LAZY)
 //   @JoinColumn(name="producto_id", nullable = false)
 //  @JsonIgnore
 //   private Producto producto;

}
