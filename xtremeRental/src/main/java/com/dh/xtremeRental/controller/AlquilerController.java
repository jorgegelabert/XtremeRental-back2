package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.AlquilerDto;
import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.service.AlquilerService;
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
@RequestMapping("/alquileres")
public class AlquilerController {
    @Autowired
    AlquilerService alquilerService;

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<AlquilerDto> crearAlquiler (@RequestBody AlquilerDto alquilerDto){
        AlquilerDto aDto = alquilerService.crear(alquilerDto);
        if(aDto != null){
            return  ResponseEntity.status(HttpStatus.OK).body(aDto);
        }
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public AlquilerDto buscarAlquilerId (@PathVariable Integer id){
        return alquilerService.buscar(id);
    }
    @CrossOrigin
    @GetMapping()
    public Set<AlquilerDto> listarAlquilers(){
        return  alquilerService.listartodos();
    }
    @CrossOrigin
    @PutMapping()
    public ResponseEntity<AlquilerDto> modificarAlquiler (@RequestBody AlquilerDto alquilerDto){
        AlquilerDto tDto = alquilerService.modificar(alquilerDto);
        if(tDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(tDto);
        }
        ResponseEntity<AlquilerDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlquiler (@PathVariable Integer id){
        String alquiler = alquilerService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(alquiler);
    }

    @CrossOrigin
    @GetMapping("/username/{username}")
    public Set<AlquilerDto> listarAlquilerUsername(@PathVariable String username){
        List<AlquilerDto> alquileresOrdenados = alquilerService.alquilerXUsername(username)
                .stream()
                .sorted(Comparator.comparing(AlquilerDto::getId))
                .collect(Collectors.toList());
        return new LinkedHashSet<>(alquileresOrdenados);
    }


}
