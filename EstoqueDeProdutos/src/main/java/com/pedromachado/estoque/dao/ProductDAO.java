package com.pedromachado.estoque.dao;

import com.pedromachado.estoque.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    String deleteProduct(Integer id);
}
