package com.dh.xtremeRental.service;


import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.entity.Producto;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductoService implements ICrudService<ProductoDto, Producto> {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public ProductoDto crear(ProductoDto productodto) {
        Producto p = mapper.convertValue(productodto,Producto.class);
        Integer operacion=1;
        Boolean validado = compruebaReglaNegocioProducto(p,operacion);
        if(validado){
        Producto productoCreado= productoRepository.save(p);
        return mapper.convertValue(productoCreado,ProductoDto.class);
        }else{
            throw new IllegalArgumentException("No se pudo crear el Producto");
        }
    }

    @Override
    public ProductoDto buscar(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            return mapper.convertValue(producto,ProductoDto.class);
        }
        return null;
    }

    @Override
    public ProductoDto modificar(ProductoDto productoDto) {
        Producto p = mapper.convertValue(productoDto,Producto.class);
        Integer operacion=2;
        Boolean validado = compruebaReglaNegocioProducto(p,operacion);
        if(validado){
            Optional<Producto> producto = productoRepository.findById(p.getId());
            if(producto.isPresent()){
                Producto pCreado= productoRepository.save(p);
                return mapper.convertValue(pCreado,ProductoDto.class);
            }else{throw new IllegalArgumentException("No se pudo pudo modificar ya que no se encontro el usuario");}
        }
       return null;
    }

    @Override
    public String eliminar(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            productoRepository.deleteById(id);
            return "Producto eliminado correctamente";
        } else { throw new IllegalArgumentException("No se pudo eliminar. Producto no encontrado");
        }
    }
    @Override
    public Set<ProductoDto> listartodos() {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoDto> productosDto = new HashSet<>();
        for (Producto p: productos) {
            productosDto.add(mapper.convertValue(p, ProductoDto.class));
        }
        return productosDto;
    }


    private Boolean compruebaReglaNegocioProducto(Producto p, Integer operacion ) {

        LocalDate fechaActual = LocalDate.now();

        if (p.getId() != null && operacion==1) {
            throw new IllegalArgumentException("No se puede Ingresar un Id");
        } else if (p.getId() == null && operacion==2) {
            throw new IllegalArgumentException("Para modificar un elemento es necesario el Id");
        } else if (p.getNombreProducto() == null || p.getNombreProducto().isEmpty()) {
            throw new IllegalArgumentException("El campo nombre del producto no puede ser null ni estar vacio");
//        } else if (p.getDni() == null || p.getDni().isEmpty()) {
//            throw new IllegalArgumentException("El campo dni no puede ser null ni estar vacio");
//        } else if (!p.getDni().matches("[0-9]+")) {
//            throw new IllegalArgumentException("El campo dni debe contener solo numeros");
//        } else if (p.getDni().length() != 8){
//            throw new IllegalArgumentException("El campo dni debe contener 8 digitos");
//        }else if((existeDni(p.getDni()) && operacion==1)){
//            throw new IllegalArgumentException("El Dni ya se encuentra en la base de datos");
//        } else if(p.getFechaAlta().isAfter(fechaActual)){
//            throw new IllegalArgumentException("La fecha de alta no puede mayor a la fecha actual");
//        }else if(p.getFechaAlta()== null){
//            throw new IllegalArgumentException("El campo fecha alta no puede ser null ni estar vacio");
//        } else if(p.getDomicilio() == null || p.getDomicilio().isEmpty()){
//            throw new IllegalArgumentException("El campo domicilio no puede ser null ni estar vacio");
        }
        else {
            return true;
        }
    }

    public Boolean existeProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            return true;
        }
        return false;
    }

    public Boolean existeId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return true;
        }
        return false;
    }


}
