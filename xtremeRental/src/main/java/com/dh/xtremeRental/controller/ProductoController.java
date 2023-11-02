package com.dh.xtremeRental.controller;

import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.service.ImagenService;
import com.dh.xtremeRental.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    ImagenService imagenService;

    @PostMapping()
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto productoDto){
        ProductoDto pDto= productoService.crear(productoDto);

        if(pDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(pDto);
        }
        ResponseEntity<ProductoDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  response;
    }

    @GetMapping("/{id}")
    public ProductoDto buscarProductoId(@PathVariable Integer id){
        return productoService.buscar(id);
    }



    @GetMapping()
    public Set<ProductoDto> listarProductos(){
        return productoService.listartodos();
    }

    @PutMapping()
    public ResponseEntity<ProductoDto> modificarProducto(@RequestBody ProductoDto productoDto){
        ProductoDto pDto= productoService.modificar(productoDto);
        if(pDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(pDto);
        }
        ResponseEntity<ProductoDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id ){
        String producto = productoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }


}
