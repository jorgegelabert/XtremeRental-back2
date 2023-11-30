package com.dh.xtremeRental.repository;

import com.dh.xtremeRental.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {

    Integer countBy();

    @Query(value = "SELECT * FROM producto where id=:id ", nativeQuery = true)
    Optional<Producto> findById(Integer id);

    //Cambio realizado por K
    @Query(value = "SELECT * FROM producto WHERE nombre_producto LIKE %:palabra% OR descripcion_producto LIKE %:palabra% OR categoria LIKE %:palabra% order by id asc"  , nativeQuery = true)
    List<Producto> findByPalabra(@Param("palabra") String palabra);

//    @Query(value = "SELECT * FROM producto WHERE precio_por_hora LIKE %:precio%", nativeQuery = true)
//    List<Producto> findByPrecio(@Param("precio") Double precio);

    @Query(value = "SELECT * FROM producto WHERE precio_por_hora BETWEEN :precioMinimo AND :precioMaximo", nativeQuery = true)
    List<Producto> findByPrecioInRange(@Param("precioMinimo") Double precioMinimo, @Param("precioMaximo") Double precioMaximo);

   /* @Query(value = "DELETE FROM producto_subcategoria WHERE producto_id=:idProducto AND subcategoria_id=:idSubCategoria", nativeQuery = true)
    Boolean deleteByProductoSubcategoria(@Param("idProducto") Integer idProducto, @Param("idSubCategoria") Integer idSubCategoria);
    */

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM producto_subcategoria WHERE producto_id = :idProducto AND subcategoria_id = :idSubCategoria", nativeQuery = true)
    void deleteByProductoSubcategoria(@Param("idProducto") Integer idProducto, @Param("idSubCategoria") Integer idSubCategoria);


    @Query(value = "SELECT * FROM producto prod INNER JOIN producto_subcategoria sub  ON prod.id=sub.producto_id  WHERE sub.subcategoria_id = :idSubCategoria"   , nativeQuery = true)
    List<Producto> findBySubcategoria(@Param("idSubCategoria") Integer idSubCategoria);

}