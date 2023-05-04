package com.online.ecommerce.controller;

import com.online.ecommerce.common.ApiResponse;
import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.model.Category;
import com.online.ecommerce.repo.CategoryRepo;
import com.online.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/add")
    ResponseEntity<ApiResponse> addProduct(ProductDto productDto){
        Optional<Category> optId = categoryRepo.findById(productDto.getCategoryId());
        if(!optId.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder().success(false).message("Category id not present").build());
        }
        productService.saveProduct(productDto,optId.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product added"),HttpStatus.CREATED);
    }

    @GetMapping("/showProducts")
    ResponseEntity<List<ProductDto>> showAllProducts(){
        List<ProductDto> product = productService.showAllProducts();
        log.info("Products:{}",product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, ProductDto productDto) throws Exception {
        Optional<Category> optId = categoryRepo.findById(productDto.getCategoryId());
        if(!optId.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Category id not present"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(id, productDto);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"),HttpStatus.CREATED);
    }
}