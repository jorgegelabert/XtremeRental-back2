package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.FavoritoDto;
import com.dh.xtremeRental.dto.SubCategoriaDto;
import com.dh.xtremeRental.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    FavoritoService favoritoService;

    @CrossOrigin
    @PostMapping("/{idproducto}/{username}")
    public ResponseEntity<Integer> crearFavorito (@PathVariable Integer idproducto, @PathVariable String username){
        Integer favoritoId = favoritoService.crearFav(idproducto,username);
        return new ResponseEntity<>(favoritoId, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping()
    public Set<FavoritoDto> listarFavoritos() {
        Set<FavoritoDto> favoritosOrdenados = favoritoService.listartodos()
                .stream()
                .sorted(Comparator.comparing(FavoritoDto::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return favoritosOrdenados;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFavorito (@PathVariable Integer id){
        String favorito = favoritoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(favorito);
    }
}
