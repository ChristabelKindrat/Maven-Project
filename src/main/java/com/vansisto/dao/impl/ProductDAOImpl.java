package com.vansisto.dao.impl;

import com.vansisto.config.MySQLConnector;
import com.vansisto.dao.ProductDAO;
import com.vansisto.model.Product;

import java.math.BigDecimal;
import java.sql.*;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public int create(Product product) {
        int status = 400;
        String SQL = "INSERT INTO product(name, price) VALUES (?, ?)";

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());

            preparedStatement.execute();
            status = 201;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public int updateById(Product product, long id) {

        String SQL = "UPDATE product set name = ?, price = ? WHERE id=" + id;

        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int deleteById(long id) {
        int status = 400;

        try (
                Connection connection = MySQLConnector.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("DELETE FROM product WHERE id=" + id);
            status = 202;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public Product getById(long id) {
        Product product = null;

        try (
                Connection connection = MySQLConnector.getConnection();
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE id=" + id);
            while (resultSet.next()) {
                long productId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");

                product = new Product(productId, name, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
