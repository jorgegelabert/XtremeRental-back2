package com.dh.xtremeRental.controller;
import com.dh.xtremeRental.dto.UsuarioDto;
import com.dh.xtremeRental.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

        @Autowired
        UsuarioService usuarioService;

        @PostMapping()
        public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioDto usuarioDto){
            UsuarioDto uDto= usuarioService.crear(usuarioDto);
            if(uDto != null){
                return ResponseEntity.status(HttpStatus.OK).body(uDto);
            }
            ResponseEntity<UsuarioDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return  response;
        }


        @GetMapping("/{id}")
        public UsuarioDto buscarUsuarioId(@PathVariable Integer id){
            return usuarioService.buscar(id);
        }

        @GetMapping()
        public Set<UsuarioDto> listarUsuarios(){
            return usuarioService.listartodos();
        }

        @PutMapping()
        public ResponseEntity<UsuarioDto> modificarUsuario(@RequestBody UsuarioDto usuarioDto){
            UsuarioDto uDto= usuarioService.modificar(usuarioDto);
            if(uDto != null){
                return ResponseEntity.status(HttpStatus.OK).body(uDto);
            }
            ResponseEntity<UsuarioDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return  response;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id ){
            String usuario = usuarioService.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
}
