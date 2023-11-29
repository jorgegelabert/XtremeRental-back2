package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.SubCategoriaDto;
import com.dh.xtremeRental.service.SubCategoriaService;
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
@RequestMapping("/subcategoria")
public class SubCategoriaController {

    @Autowired
    SubCategoriaService subCategoriaService;

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<SubCategoriaDto> crearSubCategoria(@RequestBody SubCategoriaDto subCategoriaDto) {
        SubCategoriaDto sDto = subCategoriaService.crear(subCategoriaDto);
        if (sDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(sDto);
        }
        ResponseEntity<SubCategoriaDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public SubCategoriaDto buscarCategoriaId(@PathVariable Integer id) {
        return subCategoriaService.buscar(id);
    }

    @CrossOrigin
    @GetMapping()
    public Set<SubCategoriaDto> listarUsuarios() {

        List<SubCategoriaDto> subCategoriasOrdenadas = subCategoriaService.listartodos()
                .stream()
                .sorted(Comparator.comparing(SubCategoriaDto::getId))
                .collect(Collectors.toList());
        return new LinkedHashSet<>(subCategoriasOrdenadas);

    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<SubCategoriaDto> modificarUsuario(@RequestBody SubCategoriaDto subCategoriaDto) {
        SubCategoriaDto sDto = subCategoriaService.modificar(subCategoriaDto);
        if (sDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(sDto);
        }
        ResponseEntity<SubCategoriaDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        String subcategoria = subCategoriaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(subcategoria);
    }
}
