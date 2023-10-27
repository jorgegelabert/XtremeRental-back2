package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.ImagenDto;
import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.entity.Imagen;
import com.dh.xtremeRental.service.ImagenService;
import com.dh.xtremeRental.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    ImagenService imagenService;

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


    @GetMapping("/{id}")
    public ImagenDto buscarImagenId(@PathVariable Integer id){
        ImagenDto i =imagenService.buscar(id);


        return imagenService.buscar(id);
    }
}
