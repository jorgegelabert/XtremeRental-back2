package com.dh.xtremeRental.service;


import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.dto.FavoritoDto;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
}
