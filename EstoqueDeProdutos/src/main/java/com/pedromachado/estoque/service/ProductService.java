package com.pedromachado.estoque.service;

import com.pedromachado.estoque.dao.ProductDAO;
import com.pedromachado.estoque.db.DbConfig;
import com.pedromachado.estoque.db.DbException;
import com.pedromachado.estoque.entity.Product;

import java.sql.*;
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
        String saveProduct = "insert into estoque (name, price, quantity) values (?, ?, ?)";
        this.conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn=DbConfig.connect();
            ps = conn.prepareStatement(saveProduct, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    System.out.println(rs.getMetaData());
                    System.out.println(rs.getDouble(3));
                    System.out.println(rs.getString(2));
                    Product prod = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                    return prod;
                }
            }

        } catch (Exception e) {
            throw new DbException(e.getMessage(), e.getCause());
        } finally {
            DbConfig.closePreparedStatement(ps);
            DbConfig.closeResultSet(rs);
        }
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
