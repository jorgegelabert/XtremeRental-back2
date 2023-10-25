package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.AlquilerDto;
import com.dh.xtremeRental.service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {
    @Autowired
    AlquilerService alquilerService;

    @PostMapping()
    public ResponseEntity<AlquilerDto> crearAlquiler (@RequestBody AlquilerDto alquilerDto){
        AlquilerDto aDto = alquilerService.crear(alquilerDto);
        if(aDto != null){
            return  ResponseEntity.status(HttpStatus.OK).body(aDto);
        }
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping("/{id}")
    public AlquilerDto buscarAlquilerId (@PathVariable Integer id){
        return alquilerService.buscar(id);
    }

    @GetMapping()
    public Set<AlquilerDto> listarAlquilers(){
        return  alquilerService.listartodos();
    }

    @PutMapping()
    public ResponseEntity<AlquilerDto> modificarAlquiler (@RequestBody AlquilerDto alquilerDto){
        AlquilerDto tDto = alquilerService.modificar(alquilerDto);
        if(tDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(tDto);
        }
        ResponseEntity<AlquilerDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlquiler (@PathVariable Integer id){
        String alquiler = alquilerService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(alquiler);
    }

}
