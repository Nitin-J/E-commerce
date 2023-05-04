package com.online.ecommerce.service.impl;

import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.exception.ProductDoesNotExistException;
import com.online.ecommerce.model.Category;
import com.online.ecommerce.model.Product;
import com.online.ecommerce.repo.ProductRepo;
import com.online.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public void saveProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setUrl(productDto.getUrl());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepo.save(product);
    }

    @Override
    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setUrl(product.getUrl());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setId(product.getProductId());
        return productDto;
    }


    @Override
    public List<ProductDto> showAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product:allProducts){
            productDtoList.add(getProductDto(product));
        }
        return productDtoList;
    }

    @Override
    public boolean findProductById(int id) {
        return productRepo.findById(id).isPresent();
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) throws Exception{
        Optional<Product> optionalProduct = productRepo.findById(id);
        // throw an exception if product does not exists
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setUrl(productDto.getUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }

    @Override
    public Product checkProductId(Integer productId) throws ProductDoesNotExistException{
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductDoesNotExistException(String.format("product id: %d is invalid",productId));
        }
        return optionalProduct.get();
    }
}
