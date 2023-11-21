package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    @Query(value = "update USUARIO set rol=admin where nombre_de_usuario like  :string ", nativeQuery = true)
    Optional<User> asignaAdmin(String string);

    @Query(value = "SELECT * FROM USUARIO where dni like  %:string% ", nativeQuery = true)
    Optional<User> findByDni(String string);

    //@Modifying
    //@Query("update User u set u.role = ADMIN where u.username = :date")
    //void asignaAdmin2( String username);


    //User findByUsername2(String username);
}
