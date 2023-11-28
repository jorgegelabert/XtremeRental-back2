package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Producto;
import com.dh.xtremeRental.entity.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ISubCategoriaRepository extends JpaRepository<SubCategoria,Integer> {

    @Query(value = "SELECT * FROM subcategoria where id=:id ", nativeQuery = true)
    Optional<SubCategoria> findById(Integer id);
}
