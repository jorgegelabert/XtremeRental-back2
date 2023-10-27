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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
            throw new IllegalArgumentException("No se pudo crear la imagen");
        }
    }

    @Override
    public ImagenDto buscar(Integer id) {
        Optional<Imagen> imagen = imagenRepository.findById(id);
        if(imagen.isPresent()){
            return mapper.convertValue(imagen, ImagenDto.class);
        }
        return null;
    }

    @Override
    public ImagenDto modificar(ImagenDto imagenDto) {

        Imagen i = mapper.convertValue(imagenDto,Imagen.class);
        Optional<Imagen> imagen = imagenRepository.findById(i.getId());
        if(imagen.isPresent()){
            Imagen iCreado= imagenRepository.save(i);
            return mapper.convertValue(iCreado,ImagenDto.class);
        }else{
            throw new IllegalArgumentException("No se pudo pudo modificar ya que no se encontró la imagen");}
    };

    @Override
    public String eliminar(Integer id) {
        Optional<Imagen> imagen = imagenRepository.findById(id);
        if (imagen.isPresent()) {
            imagenRepository.deleteById(id);
            return "Imagen eliminada correctamente";
        } else {
            throw new IllegalArgumentException("No se pudo eliminar. Imagen no encontrada");
        }
    }

    @Override
    public Set<ImagenDto> listartodos() {
        List<Imagen> imagenes = imagenRepository.findAll();
        Set<ImagenDto> imagenesDto = new HashSet<>();
        for (Imagen imagen : imagenes) {
            imagenesDto.add(mapper.convertValue(imagen, ImagenDto.class));
        }
        return imagenesDto;
    }
}


