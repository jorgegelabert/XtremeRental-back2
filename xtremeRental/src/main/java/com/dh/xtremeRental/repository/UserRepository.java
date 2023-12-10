package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM USUARIO where username like %:username%", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "update USUARIO set rol=admin where nombre_de_usuario like  :string ", nativeQuery = true)
    Optional<User> asignaAdmin(String string);

    @Query(value = "SELECT * FROM USUARIO where dni like  %:string% ", nativeQuery = true)
    Optional<User> findByDni(String string);

    @Query(value = "SELECT * FROM USUARIO where id=:id ", nativeQuery = true)
    Optional<User> findById(Integer id);

//    @Query("SELECT COUNT(u) FROM USUARIO u WHERE u.role = :role")
//    Integer countByRole(@Param("role") Role role);

}
