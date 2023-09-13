package com.macv.market.persistence.crud;

import com.macv.market.persistence.entity.Producto;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    //Buscar productos por categoria
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Buscar productos por menor stock y activos (estado = true)
    List<Producto> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
