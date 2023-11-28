package com.dh.xtremeRental.service;


import com.dh.xtremeRental.dto.SubCategoriaDto;
import com.dh.xtremeRental.entity.SubCategoria;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.ISubCategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubCategoriaService implements ICrudService<SubCategoriaDto,Integer> {

    @Autowired
    private ISubCategoriaRepository iSubCategoriaRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public SubCategoriaDto crear(SubCategoriaDto subCategoriaDto) {
        SubCategoria s = mapper.convertValue(subCategoriaDto,SubCategoria.class);
        SubCategoria subCategoriaCreada= iSubCategoriaRepository.save(s);
        if(subCategoriaCreada !=null){
            return mapper.convertValue(subCategoriaCreada, SubCategoriaDto.class);}
        else{
            throw new IllegalArgumentException("No se pudo crear la imagen");
        }
    }

    @Override
    public SubCategoriaDto buscar(Integer id) {
        Optional<SubCategoria> subCategoria = iSubCategoriaRepository.findById(id);
        if(subCategoria.isPresent()){
            return mapper.convertValue(subCategoria, SubCategoriaDto.class);
        }
        return null;
    }

    @Override
    public SubCategoriaDto modificar(SubCategoriaDto subCategoriaDto) {
        SubCategoria s = mapper.convertValue(subCategoriaDto,SubCategoria.class);
        Optional<SubCategoria> subCategoria = iSubCategoriaRepository.findById(s.getId());
        if(subCategoria.isPresent()){
            SubCategoria sCreado= iSubCategoriaRepository.save(s);
            return mapper.convertValue(sCreado,SubCategoriaDto.class);
        }else{
            throw new IllegalArgumentException("No se pudo pudo modificar ya que no se encontró la imagen");}
    };


    @Override
    public String eliminar(Integer id) {
        Optional<SubCategoria> subCategoria = iSubCategoriaRepository.findById(id);
        if (subCategoria.isPresent()) {
            iSubCategoriaRepository.deleteById(id);
            return "SubCategoria eliminada correctamente";
        } else {
            throw new IllegalArgumentException("No se pudo eliminar. SubCategoria no encontrada");
        }
    }

    @Override
    public Set<SubCategoriaDto> listartodos() {
        List<SubCategoria> subCategorias = iSubCategoriaRepository.findAll();
        Set<SubCategoriaDto> subCategoriasDto = new HashSet<>();
        for (SubCategoria subCategoria : subCategorias) {
            subCategoriasDto.add(mapper.convertValue(subCategoria, SubCategoriaDto.class));
        }
        return subCategoriasDto;
    }

}
