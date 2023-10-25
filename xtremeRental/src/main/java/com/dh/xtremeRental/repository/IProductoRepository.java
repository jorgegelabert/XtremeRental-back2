package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {

    @Query(value = "SELECT * FROM PRODUCTO where id=:id ", nativeQuery = true)
    Optional<Producto> findById(Integer id);

}
