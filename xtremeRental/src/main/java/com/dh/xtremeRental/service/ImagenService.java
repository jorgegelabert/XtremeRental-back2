package com.dh.xtremeRental.service;

import com.dh.xtremeRental.dto.AlquilerDto;
import com.dh.xtremeRental.dto.ImagenDto;
import com.dh.xtremeRental.entity.Alquiler;
import com.dh.xtremeRental.entity.Imagen;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.IAlquilerRepository;
import com.dh.xtremeRental.repository.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

@Service
public class ImagenService implements ICrudService<ImagenDto, Imagen>{


    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    ObjectMapper mapper;



    @Override
    public ImagenDto crear(ImagenDto imagenDto) {
        Imagen i = mapper.convertValue(imagenDto,Imagen.class);
        Imagen imagenCreada= imagenRepository.save(i);
        if(imagenCreada !=null){
        return mapper.convertValue(imagenCreada, ImagenDto.class);}
        else{
        throw new IllegalArgumentException("No se pudo crear el alquiler");
        }
    }



    @Override
    public ImagenDto buscar(Integer id) {
        return null;
    }

    @Override
    public ImagenDto modificar(ImagenDto imagenDto) {
        return null;
    }

    @Override
    public String eliminar(Integer id) {
        return null;
    }

    @Override
    public Set<ImagenDto> listartodos() {
        return null;
    }
}


