package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.AlquilerDto;
import com.dh.xtremeRental.dto.ImagenDto;
import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.entity.Imagen;
import com.dh.xtremeRental.service.ImagenService;
import com.dh.xtremeRental.service.ProductoService;
import com.dh.xtremeRental.service.S3Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    ImagenService imagenService;

    @Autowired
    private S3Service s3Service;



/*
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("data") MultipartFile file, Model model) throws IOException {
        if (file != null && !file.isEmpty()) {
            ImagenDto image = new ImagenDto();
            image.setName(file.getOriginalFilename());
            image.setData(file.getBytes());
            imagenService.crear(image);
        }
        return "redirect:/";
    }

   /* @GetMapping("/{id}")
    public ImagenDto buscarImagen (@PathVariable Integer id){
        return imagenService.buscar(id);
    }


    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer imageId) {

        ImagenDto image = imagenService.buscar(imageId);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public String modificarImagen(@PathVariable Integer id, @RequestParam("data") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            ImagenDto imagen = imagenService.buscar(id);

            if (imagen != null) {
                // Realizar las modificaciones necesarias en la imagen
                imagen.setName(file.getOriginalFilename());
                imagen.setData(file.getBytes());
                imagenService.modificar(imagen);
                return "Imagen modificada exitosamente";
            }
        }
        return "Error al modificar la imagen";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarImagen (@PathVariable Integer id){
        String imagen = imagenService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(imagen);
    }
    */
    @GetMapping()
    public Set<ImagenDto> listarImagenes(){

        return (Set<ImagenDto>) imagenService.listartodos()
                .stream()
                .peek( imagen -> imagen.setImagenUrl(s3Service.getObjectURL(imagen.getImagenPath())))
                .collect(Collectors.toList());
    }

    @PostMapping
    ImagenDto create(@RequestBody ImagenDto imagenDto){
        imagenDto.setImagenPath(s3Service.getObjectURL(imagenDto.getImagenPath()));
        ImagenDto imagenCreada = imagenService.crear(imagenDto);

        //imagenDto.setImagenUrl(s3Service.getObjectURL(imagenDto.getImagenPath()));
        return imagenCreada;
    }


}
