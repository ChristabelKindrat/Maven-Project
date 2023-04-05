package com.vansisto.service.impl;

import com.vansisto.dao.ProductDAO;
import com.vansisto.dao.impl.ProductDAOImpl;
import com.vansisto.model.Product;
import com.vansisto.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public int create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product getById(long id) {
        return productDAO.getById(id);
    }

    @Override
    public int updateById(Product product,long id) {
        return productDAO.updateById(product, id);
    }

    @Override
    public int deleteById(long id) {
        return productDAO.deleteById(id);
    }

}
