package com.dh.xtremeRental.controller;
import com.dh.xtremeRental.dto.UserDto;
import com.dh.xtremeRental.service.UserService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

        @Autowired
        UserService usuarioService;
    @CrossOrigin
        @PostMapping()
        public ResponseEntity<UserDto> crearUsuario(@RequestBody UserDto userDto){
            UserDto uDto= usuarioService.crear(userDto);
            if(uDto != null){
                return ResponseEntity.status(HttpStatus.OK).body(uDto);
            }
            ResponseEntity<UserDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return  response;
        }

    @CrossOrigin
        @GetMapping("/{id}")
        public UserDto buscarUsuarioId(@PathVariable Integer id){
            return usuarioService.buscar(id);
        }
    @CrossOrigin
        @GetMapping()
        public Set<UserDto> listarUsuarios(){

        List<UserDto> usuariosOrdenados = usuarioService.listartodos()
                .stream()
                .sorted(Comparator.comparing(UserDto::getId))
                .collect(Collectors.toList());
        return new LinkedHashSet<>(usuariosOrdenados);

        }
    @CrossOrigin
        @PutMapping()
        public ResponseEntity<UserDto> modificarUsuario(@RequestBody UserDto userDto){
            UserDto uDto= usuarioService.modificar(userDto);
            if(uDto != null){
                return ResponseEntity.status(HttpStatus.OK).body(uDto);
            }
            ResponseEntity<UserDto> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return  response;
        }
    @CrossOrigin
        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id ){
            String usuario = usuarioService.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }

    @CrossOrigin
    @GetMapping("/usuario/{nombreUsuario}")
    public UserDto buscarnombreUsuario(@PathVariable String nombreUsuario){
        return usuarioService.buscarNombreUsuario(nombreUsuario);
    }


    /*@CrossOrigin
    @PostMapping("/admin/{nombreUsuario}")
    public String asignaAdmin(@PathVariable String nombreUsuario){
        return usuarioService.asignaAdmin(nombreUsuario);
    }*/

}
