package com.dh.xtremeRental.entity;

import com.dh.xtremeRental.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "favoritos")
public class Favorito {

    //SE HACE POR USERNAME EL BUSCADOR
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="producto_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User usuario;

    @Override
    public int hashCode() {
        return Objects.hash(id, producto, usuario);
    }
}
