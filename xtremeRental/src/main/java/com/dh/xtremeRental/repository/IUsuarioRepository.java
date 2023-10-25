package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM USUARIO where dni like  %:string% ", nativeQuery = true)
    Optional<Usuario> findByDni(String string);

}
