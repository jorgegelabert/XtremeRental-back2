package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Alquiler;
import com.dh.xtremeRental.entity.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAlquilerRepository extends JpaRepository<Alquiler,Integer> {

    @Query(value = "SELECT * FROM ALQUILER where fechaAltaAlquiler=:fechaAltaAlquiler and usuario_id=:id", nativeQuery = true)
    Optional<Alquiler> alquilerDia(LocalDate fecha, Integer id);

    @Query(value = "SELECT * FROM ALQUILER where fechaAltaAlquiler=:fechaAltaAlquiler and producto_id=:id", nativeQuery = true)
    Optional<Alquiler> alquilerMismoDia(LocalDate fecha, Integer id);

    @Query(value = "SELECT * FROM alquiler WHERE usuario_id=:id ", nativeQuery = true)
    List<Alquiler> findByUserId(Integer id);

}