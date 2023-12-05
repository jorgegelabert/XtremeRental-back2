package com.dh.xtremeRental.service;


import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.dto.FavoritoDto;
import com.dh.xtremeRental.dto.ProductoDto;
import com.dh.xtremeRental.dto.SubCategoriaDto;
import com.dh.xtremeRental.entity.Alquiler;
import com.dh.xtremeRental.entity.Favorito;
import com.dh.xtremeRental.entity.Producto;
import com.dh.xtremeRental.entity.SubCategoria;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.IFavoritoRespository;
import com.dh.xtremeRental.repository.IProductoRepository;
import com.dh.xtremeRental.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoritoService implements ICrudService<FavoritoDto, Favorito> {

    @Autowired
    private IFavoritoRespository favoritoRespository;

    @Autowired
    private UserService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;
    @Autowired
    private IFavoritoRespository favoritoRepository;


    public Integer crearFav(Integer idproducto, String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        Optional<Producto> productoOptional = productoRepository.findById(idproducto);

        if (userOptional.isPresent() && productoOptional.isPresent()) {
            User usuario = userOptional.get();
            Producto producto = productoOptional.get();

            // Verificar si ya existe un favorito con el mismo producto y usuario
            boolean existeFavorito = usuario.getFavoritos().stream()
                    .anyMatch(fav -> fav.getProducto().equals(producto));

            if (existeFavorito) {
                // Manejar si ya existe un favorito con el mismo producto y usuario
                return null;
            }

            Favorito favorito = new Favorito();
            favorito.setUsuario(usuario);
            favorito.setProducto(producto);

            usuario.getFavoritos().add(favorito);

            Favorito favoritoGuardado = favoritoRepository.save(favorito);
            return favoritoGuardado.getId();
        } else {
            // Manejar si no se encuentra el usuario o el producto
            return null;
        }
    }


    @Transactional
    public String eliminarFav(Integer idproducto, String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        Optional<Producto> productoOptional = productoRepository.findById(idproducto);

        if (userOptional.isPresent() && productoOptional.isPresent()) {
            Integer usuario = userOptional.get().getId();
            Integer producto = productoOptional.get().getId();

            favoritoRepository.deleteFav(usuario, producto);
            return "Favorito eliminado correctamente";
        } else {
            throw new IllegalArgumentException("No se pudo eliminar. Favorito no encontrado");
        }
    }

    @Override
    public FavoritoDto crear(FavoritoDto favoritoDto) {
        return null;
    }

    @Override
    public FavoritoDto buscar(Integer id) {
        return null;
    }

    @Override
    public FavoritoDto modificar(FavoritoDto favoritoDto) {
        return null;
    }

    @Override
    public String eliminar(Integer id) {
        return null;
    }


    @Override
    public Set<FavoritoDto> listartodos() {
        List<Favorito> favoritos = favoritoRepository.findAll();

        // Forzar la inicialización de las asociaciones antes de la serialización
        favoritos.forEach(favorito -> {
            Hibernate.initialize(favorito.getUsuario());
            Hibernate.initialize(favorito.getProducto());
        });

        // Convertir a DTOs después de inicializar las asociaciones
        Set<FavoritoDto> favoritosDtos = new HashSet<>();
        for (Favorito f : favoritos) {

            favoritosDtos.add(mapper.convertValue(f, FavoritoDto.class));
        }
        return favoritosDtos;
    }

    public Set<FavoritoDto> listarPorUsuario(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User usuario = userOptional.get();

            return usuario.getFavoritos().stream()
                    .map(this::convertToFavoritoDto) // Método para convertir Favorito a FavoritoDto
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else {
            // Manejar si no se encuentra el usuario
            throw new IllegalArgumentException("Usuario no encontrado: " + username);
            // Puedes definir tu propia excepción para manejar este caso
        }
    }



    public Set<FavoritoDto> listarFavoritosPorUsuario(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User usuario = userOptional.get();

            Set<FavoritoDto> favoritosPorUsuario = usuario.getFavoritos().stream()
                    .map(favorito -> convertToFavoritoDto(favorito)) // Método para convertir Favorito a FavoritoDto
                    .sorted(Comparator.comparing(FavoritoDto::getId))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            return favoritosPorUsuario;
        } else {
            // Manejar si no se encuentra el usuario
            return Collections.emptySet(); // O podrías devolver null o un mensaje de error, según tu lógica de manejo de errores
        }
    }

    private FavoritoDto convertToFavoritoDto(Favorito favorito) {
        FavoritoDto favoritoDto = new FavoritoDto();
        favoritoDto.setId(favorito.getId());

        // Si tu entidad Favorito tiene una relación con Usuario y Producto, puedes obtener los datos aquí
        // Asumiendo que Favorito tiene un campo usuario y producto y que deseas devolver los ID en FavoritoDto
        if (favorito.getUsuario() != null) {
            favoritoDto.setId(favorito.getUsuario().getId()); // Reemplaza esto con el atributo que representa el ID del usuario en FavoritoDto
            // Puedes agregar más detalles del usuario aquí si los necesitas
        }
        if (favorito.getProducto() != null) {
            favoritoDto.setId(favorito.getProducto().getId()); // Reemplaza esto con el atributo que representa el ID del producto en FavoritoDto
            // Puedes agregar más detalles del producto aquí si los necesitas
        }

        return favoritoDto;
    }

    public FavoritoDto asignaProducto(Integer idFav, Integer idProducto){
        Optional<Favorito> favorito = favoritoRepository.findById(idFav);
        if(favorito.isPresent()){
            Optional<Producto> producto = productoRepository.findById(idProducto);
            if(producto.isPresent()){
                Favorito     fav = favorito.get();
                Producto     produc = producto.get();
                fav.setProducto(produc);
                favoritoRepository.save(fav);
                return mapper.convertValue(fav,FavoritoDto.class);
            }else {throw new IllegalArgumentException("No se ha encontrado el producto");}
        } else {throw new IllegalArgumentException("No se ha encontrado el favorito");}
    }



}
