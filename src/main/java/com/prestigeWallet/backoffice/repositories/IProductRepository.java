package com.prestigeWallet.backoffice.repositories;


import com.prestigeWallet.backoffice.entities.Category;
import com.prestigeWallet.backoffice.entities.Product;

import java.util.List;

public interface IProductRepository {

    Product getById(Integer id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Category category);
    List<Product> getAllWallets();
    void add(Product product);
}
