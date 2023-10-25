package com.dh.xtremeRental.service;

import com.dh.xtremeRental.dto.UsuarioDto;
import com.dh.xtremeRental.entity.Usuario;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.IUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements ICrudService<UsuarioDto, Usuario> {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public UsuarioDto crear(UsuarioDto usuarioDto) {
        Usuario u = mapper.convertValue(usuarioDto,Usuario.class);
        Integer operacion=1;
        Boolean validado = compruebaReglaNegocioUsuario(u,operacion);
        if(validado){
            Usuario usuarioCreado= usuarioRepository.save(u);
            return mapper.convertValue(usuarioCreado,UsuarioDto.class);
        }
        return null;
    }

    @Override
    public UsuarioDto buscar(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return mapper.convertValue(usuario,UsuarioDto.class);
        }
        return null;
    }

    @Override
    public UsuarioDto modificar(UsuarioDto usuarioDto) {
        Usuario u = mapper.convertValue(usuarioDto,Usuario.class);
        Integer operacion=2;
        Boolean validado = compruebaReglaNegocioUsuario(u,operacion);
        if(validado) {
            Optional<Usuario> usuario = usuarioRepository.findById(u.getId());
            if(usuario.isPresent()){
                Usuario uCreado= usuarioRepository.save(u);
                return mapper.convertValue(uCreado,UsuarioDto.class);
            }
        }
        return null;
    }

    @Override
    public String eliminar(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return "Usuario eliminado correctamente";
        }
        else { throw new IllegalArgumentException("No se pudo eliminar. Usuario no encontrado");
        }
    }

    @Override
    public Set<UsuarioDto> listartodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Set<UsuarioDto> usuariosDto = new HashSet<>();
        for (Usuario u : usuarios) {
            usuariosDto.add(mapper.convertValue(u, UsuarioDto.class));
        }
        return usuariosDto;
    }


    private Boolean compruebaReglaNegocioUsuario(Usuario u, Integer operacion ) {

        LocalDate fechaActual = LocalDate.now();

//        if (u.getId() != null && operacion==1) {
//            throw new IllegalArgumentException("No se puede Ingresar un Id");
//        } else if (u.getId() == null && operacion==2) {
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
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return true;
        }
        return false;
    }

    public Boolean existeDni(String dni) {
        Optional<Usuario> usuario = usuarioRepository.findByDni(dni);
        if (usuario.isPresent()) {
            return true;
        }
        return false;
    }

}
