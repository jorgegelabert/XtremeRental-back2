package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {

    @Query(value = "SELECT * FROM producto where id=:id ", nativeQuery = true)
    Optional<Producto> findById(Integer id);

    @Query(value = "SELECT * FROM producto WHERE nombre_producto LIKE %:palabra% OR descripcion_producto LIKE %:palabra% OR categoria LIKE %:palabra% order by id asc"  , nativeQuery = true)
    List<Producto> findByPalabra(@Param("palabra") String palabra);

}