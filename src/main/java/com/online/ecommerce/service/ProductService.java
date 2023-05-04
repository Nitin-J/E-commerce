package com.online.ecommerce.service;

import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.model.Category;
import com.online.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto, Category category);
    List<ProductDto> showAllProducts();
    boolean findProductById(int id);
    void updateProduct(Integer id, ProductDto productDto) throws Exception;

    ProductDto getProductDto(Product product);

    Product checkProductId(Integer productId);
}
