package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFavoritoRespository extends JpaRepository<Favorito,Integer> {

}
