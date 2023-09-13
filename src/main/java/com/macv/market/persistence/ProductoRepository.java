package com.macv.market.persistence;

import com.macv.market.domain.Product;
import com.macv.market.domain.repository.ProductRepository;
import com.macv.market.persistence.crud.ProductoCrudRepository;
import com.macv.market.persistence.entity.Producto;
import com.macv.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public List<Product> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return mapper.toProducts(productos);
    }

    @Override
    public List<Product> getScarseProducts(int stockQuantity) {
        List<Producto> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(stockQuantity, true);
        return mapper.toProducts(productos);
    }

    //En este caso ProductoCrudRepository por defecto ya implementa findById
    public Optional<Producto> getProductoById(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }
    @Override
    public Optional<Product> getById(int productId) {
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(p -> mapper.toProduct(p));
    }

    //En este caso ProductoCrudRepository por defecto ya implementa save
    public Producto saveProducto(Producto producto){
        return productoCrudRepository.save(producto);
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    //En este caso ProductoCrudRepository por defecto ya implementa deleteById
    public void deleteProducto(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

    @Override
    public void delete(int productId) {

    }
}
