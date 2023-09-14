package com.macv.market.persistence;

import com.macv.market.domain.Product;
import com.macv.market.domain.repository.ProductRepository;
import com.macv.market.persistence.crud.ProductoCrudRepository;
import com.macv.market.persistence.entity.Producto;
import com.macv.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
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

    @Override
    public Optional<Product> getById(int productId) {
        Optional<Producto> producto = productoCrudRepository.findById(productId);
        return producto.map(p -> mapper.toProduct(p));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
