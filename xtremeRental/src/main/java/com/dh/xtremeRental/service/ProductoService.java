package com.dh.xtremeRental.service;


import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.entity.Producto;
import com.dh.xtremeRental.entity.SubCategoria;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.IProductoRepository;
import com.dh.xtremeRental.repository.ISubCategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductoService implements ICrudService<ProductoDto, Producto> {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ISubCategoriaRepository iSubCategoriaRepository;

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

    public Set<ProductoDto> buscarPorPalabra(String palabra) {
        List<Producto> productos = productoRepository.findByPalabra(palabra);
        Set<ProductoDto> productosDto = new HashSet<>();
        for (Producto p: productos) {
            productosDto.add(mapper.convertValue(p, ProductoDto.class));
        }
        return productosDto;
    }

//    public Set<ProductoDto> buscarPorPrecio(Double precio) {
//        List<Producto> productos = productoRepository.findByPrecio(precio);
//        Set<ProductoDto> productosDto = new HashSet<>();
//        for (Producto p: productos) {
//            productosDto.add(mapper.convertValue(p, ProductoDto.class));
//        }
//        return productosDto;
//    }

    public List<ProductoDto> buscarPorPrecio(Double precio) {
        // Define un rango de búsqueda considerando decimales
        Double rango = 0.1; // Esto puede variar según la precisión que necesites

        // Calcular los límites del rango
        Double precioMinimo = precio - rango;
        Double precioMaximo = precio + rango;

        // Realizar la búsqueda por un rango de precios en la base de datos
        List<Producto> productos = productoRepository.findByPrecioInRange(precioMinimo, precioMaximo);

        // Mapear los productos a DTOs
        List<ProductoDto> productosDto = productos.stream()
                .map(producto -> mapper.convertValue(producto, ProductoDto.class))
                .sorted(Comparator.comparing(ProductoDto::getId))
                .collect(Collectors.toList());

        return productosDto;
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

    public ProductoDto asignaSubcategoria(Integer idProducto, Integer idCategoria){
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()){
            Optional<SubCategoria> subcategoria = iSubCategoriaRepository.findById(idCategoria);
            if(subcategoria.isPresent()){
                Producto     produc = producto.get();
                SubCategoria subcat = subcategoria.get();
                produc.getSubcategorias().add(subcat);
                productoRepository.save(produc);
                return mapper.convertValue(produc,ProductoDto.class);
            }else {throw new IllegalArgumentException("No se ha encontrado la subcategoria");}
        } else {throw new IllegalArgumentException("No se ha encontrado el producto");}
    }


    public ProductoDto borraSubcategoria(Integer idproducto, Integer idcategoria) {
        Optional<Producto> producto = productoRepository.findById(idproducto);
        if(producto.isPresent()){
            Optional<SubCategoria> subcategoria = iSubCategoriaRepository.findById(idcategoria);
            if(subcategoria.isPresent()){
                productoRepository.deleteByProductoSubcategoria(idproducto,idcategoria);
                    Optional<Producto> p = productoRepository.findById(idproducto);
                    return mapper.convertValue(p,ProductoDto.class);
            }else {throw new IllegalArgumentException("No se ha encontrado la subcategoria");}
        } else {throw new IllegalArgumentException("No se ha encontrado el producto");}
    }


    public Set<ProductoDto> buscarPorSubcategoria(Integer subcat) {
        List<Producto> productos = productoRepository.findBySubcategoria(subcat);
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
