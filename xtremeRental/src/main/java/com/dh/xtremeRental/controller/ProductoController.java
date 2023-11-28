package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.service.ImagenService;
import com.dh.xtremeRental.service.ProductoService;
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
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @Autowired
    ImagenService imagenService;

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto productoDto){
        ProductoDto pDto= productoService.crear(productoDto);

        if(pDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(pDto);
        }
        ResponseEntity<ProductoDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  response;
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ProductoDto buscarProductoId(@PathVariable Integer id){
        return productoService.buscar(id);
    }


    @CrossOrigin
    @GetMapping("/busqueda/{palabra}")
    public Set<ProductoDto> buscarProductoPalabra(@PathVariable String palabra){

        List<ProductoDto> productosOrdenados = productoService.buscarPorPalabra(palabra)
                .stream()
                .sorted(Comparator.comparing(ProductoDto::getId))
                .collect(Collectors.toList());

        return new LinkedHashSet<>(productosOrdenados);
    }

    @CrossOrigin
    @GetMapping("/busquedaPorPrecio/{precio}")
    public Set<ProductoDto> buscarProductoPrecio(@PathVariable Double precio){

        List<ProductoDto> productosOrdenados = productoService.buscarPorPrecio(precio)
                .stream()
                .sorted(Comparator.comparing(ProductoDto::getId))
                .collect(Collectors.toList());

        return new LinkedHashSet<>(productosOrdenados);
    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<ProductoDto> modificarProducto(@RequestBody ProductoDto productoDto){
        ProductoDto pDto= productoService.modificar(productoDto);
        if(pDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(pDto);
        }
        ResponseEntity<ProductoDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  response;
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id ){
        String producto = productoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @CrossOrigin
    @GetMapping()
    public Set<ProductoDto> listarProductos() {
        List<ProductoDto> productosOrdenados = productoService.listartodos()
                .stream()
                .sorted(Comparator.comparing(ProductoDto::getId))
                .collect(Collectors.toList());

        return new LinkedHashSet<>(productosOrdenados);
    }
}
