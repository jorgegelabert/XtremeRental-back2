package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.entity.Favorito;
import com.dh.xtremeRental.entity.Producto;
import com.dh.xtremeRental.entity.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IFavoritoRespository extends JpaRepository<Favorito,Integer> {
    @Modifying
    @Query(value = "DELETE FROM favoritos WHERE producto_id = :id_producto AND usuario_id = :id_usuario", nativeQuery = true)
    void deleteFav(Integer id_producto, Integer id_usuario);



}
