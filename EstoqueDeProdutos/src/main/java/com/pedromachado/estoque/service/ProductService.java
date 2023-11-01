package com.pedromachado.estoque.service;

import com.pedromachado.estoque.dao.ProductDAO;
import com.pedromachado.estoque.db.DbConfig;
import com.pedromachado.estoque.db.DbException;
import com.pedromachado.estoque.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ProductDAO {

    public Connection conn;

    public ProductService(Connection connect) {
        this.conn = connect;
    }

    @Override
    public List<Product> getAll() {
        String getAll = "select * from estoque order by id";
        conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<Product>();

        try {
            conn = DbConfig.connect();
            ps = conn.prepareStatement(getAll);
            rs = ps.executeQuery();

            while(rs.next()) {
                Product prod = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                products.add(prod);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e.getCause());
        } finally {
            DbConfig.closePreparedStatement(ps);
            DbConfig.closeResultSet(rs);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public String deleteProduct(Integer id) {
        return null;
    }
}
