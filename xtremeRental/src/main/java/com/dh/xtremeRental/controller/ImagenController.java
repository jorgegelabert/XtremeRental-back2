package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.ImagenDto;
import com.dh.xtremeRental.service.ImagenService;
import com.dh.xtremeRental.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
    @GetMapping()
    public Set<ImagenDto> listarImagenes(){

        return (Set<ImagenDto>) imagenService.listartodos()
                .stream()
                .peek( imagen -> imagen.setUrl(s3Service.getObjectURL(imagen.getUrl())))
                .collect(Collectors.toList());
    }
    @CrossOrigin
    @PostMapping
    ImagenDto create(@RequestBody ImagenDto imagenDto){
        imagenDto.setUrl(s3Service.getObjectURL(imagenDto.getUrl()));
        ImagenDto imagenCreada = imagenService.crear(imagenDto);

        //imagenDto.setImagenUrl(s3Service.getObjectURL(imagenDto.getImagenPath()));
        return imagenCreada;
    }


}
