package com.vansisto.dao;

import com.vansisto.model.Product;

public interface ProductDAO {
    int create(Product product);
    Product getById(long id);
    int updateById(Product product, long id);
    int deleteById(long id);
}
