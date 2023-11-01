package com.pedromachado.estoque.dao;

import com.pedromachado.estoque.db.DbConfig;
import com.pedromachado.estoque.service.ProductService;

public class DAOFactory {

    public static ProductDAO createProductDAO() {
        return new ProductService(DbConfig.connect());
    }
}
