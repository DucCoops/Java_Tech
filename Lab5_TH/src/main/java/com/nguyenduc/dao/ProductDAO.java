package com.nguyenduc.dao;

import com.nguyenduc.repository.IProduct;
import com.nguyenduc.model.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProduct {
    public ProductDAO(Class<Product> clazz) {
        super(clazz);
    }

    @Override
    public List<Product> getAll() {
        String hql = "FROM Product";
        return this.query(hql);
    }

}
