package com.vansisto.service;

import com.vansisto.model.Product;

public interface ProductService {
    int create(Product product);
    Product getById(long id);
    int updateById(Product product, long id);
    int deleteById(long id);
}
