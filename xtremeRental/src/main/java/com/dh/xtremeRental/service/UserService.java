package com.dh.xtremeRental.service;

import com.dh.xtremeRental.dto.UserDto;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements ICrudService<UserDto, User> {
    @Autowired
    private UserRepository userRepository;



    @Autowired
    ObjectMapper mapper;


    @Override
    public UserDto crear(UserDto userDto) {
        User u = mapper.convertValue(userDto,User.class);
        Integer operacion=1;
        Boolean validado = compruebaReglaNegocioUsuario(u,operacion);
        if(validado){
            User usuarioCreado= userRepository.save(u);
            return mapper.convertValue(usuarioCreado, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto buscar(Integer id) {
        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            return mapper.convertValue(usuario, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto modificar(UserDto userDto) {
        User u = mapper.convertValue(userDto,User.class);
        Integer operacion=2;
        Boolean validado = compruebaReglaNegocioUsuario(u,operacion);
        if(validado) {
            Optional<User> usuario = userRepository.findById(u.getId());
            if(usuario.isPresent()){
                User uCreado= userRepository.save(u);
                return mapper.convertValue(uCreado, UserDto.class);
            }
        }
        return null;
    }

    @Override
    public String eliminar(Integer id) {
        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            userRepository.deleteById(id);
            return "Usuario eliminado correctamente";
        }
        else { throw new IllegalArgumentException("No se pudo eliminar. Usuario no encontrado");
        }
    }

    @Override
    public Set<UserDto> listartodos() {
        List<User> usuarios = userRepository.findAll();
        Set<UserDto> usuariosDto = new HashSet<>();
        for (User u : usuarios) {
            usuariosDto.add(mapper.convertValue(u, UserDto.class));
        }
        return usuariosDto;
    }


    private Boolean compruebaReglaNegocioUsuario(User u, Integer operacion ) {

 if (u.getId() != null && operacion==1) {
throw new IllegalArgumentException("No se puede Ingresar un Id");
 } else if ((u.getRole().equals("ADMIN")) && operacion==1) {
            throw new IllegalArgumentException("No te hagas el vivo. No se puede crear un usuario admin");
        }
 //else if (u.getId() == null && operacion==2) {
//            throw new IllegalArgumentException("Para modificar un elemento es necesario el Id");
//        } else if (u.getApellido() == null || u.getApellido().isEmpty()) {
//            throw new IllegalArgumentException("El campo apellido no puede ser null ni estar vacio");
//        } else if (u.getNombre() == null || u.getNombre().isEmpty()) {
//            throw new IllegalArgumentException("El campo nombre no puede ser null ni estar vacio");
//        }else if (u.getMatricula() == null ) {
//            throw new IllegalArgumentException("El campo matricula no puede ser null ni estar vacio");
//        }else if (!(u.getApellido().length() >= 3)) {
//            throw new IllegalArgumentException("El campo apellido debe tener como minimo 3 caracteres");
//        }else if (!(u.getNombre().length() >= 3)) {
//            throw new IllegalArgumentException("El campo nombre debe tener como minimo 3 caracteres");
//        }else if (u.getMatricula() == null || o.getMatricula()<0) {
//            throw new IllegalArgumentException("El campo matricula no puede ser null y debe ser mayor a 0");
//        }else if ((existeMatricula(u.getMatricula()) && operacion ==1)) {
//            throw new IllegalArgumentException("La matricula ya existe");
//        }
//        else {
//            return true;
//        }
        return true;
    }


    public Boolean existeUsuario(Integer id) {
        Optional<User> usuario = userRepository.findById(id);
        if(usuario.isPresent()){
            return true;
        }
        return false;
    }

    public Boolean existeDni(String dni) {
        Optional<User> usuario = userRepository.findByDni(dni);
        if (usuario.isPresent()) {
            return true;
        }
        return false;
    }


    public UserDto buscarNombreUsuario(String username) {
        Optional<User> usuario = userRepository.findByUsername(username);
        if(usuario.isPresent()){
            return mapper.convertValue(usuario, UserDto.class);
        }
        return null;
    }

    /*public String asignaAdmin(String username) {
        User usuario = userRepository.findByUsername2(username);
        if(usuario.getRole().equals("ADMIN")){
            return "Se modifico correctamente";
        }
        return "No se modificò";
    }*/

}
