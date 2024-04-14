package com.prestigeWallet.backoffice.services;


import com.prestigeWallet.backoffice.dto.CategoryDTO;
import com.prestigeWallet.backoffice.dto.ProductDTO;
import com.prestigeWallet.backoffice.dto.ProductLightDTO;

import java.util.List;

public interface IProductService {

    ProductDTO getProductById(Integer id);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO);
    List<ProductLightDTO> getProductsLightDTOByCategory(CategoryDTO categoryDTO);
    List<ProductLightDTO> getWallets();
    void addProduct(ProductDTO productDTO);
}
