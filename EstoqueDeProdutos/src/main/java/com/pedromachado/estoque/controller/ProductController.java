package com.pedromachado.estoque.controller;

import com.google.gson.Gson;
import com.pedromachado.estoque.dao.DAOFactory;
import com.pedromachado.estoque.dao.ProductDAO;
import com.pedromachado.estoque.db.DbConfig;
import com.pedromachado.estoque.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "ProductController", urlPatterns = "/produto")

public class ProductController extends HttpServlet {

    Gson gson = new Gson();

    ProductDAO productDAO = DAOFactory.createProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(200);

        Connection conn = DbConfig.connect();
        DbConfig.closeConnection(conn);

//        Product product =  new Product(1,"Chocolate", 8.50, 2);

        PrintWriter pw = resp.getWriter();
        pw.print(gson.toJson(productDAO.getAll()));

        pw.flush();
    }
}
