package com.online.ecommerce.controller;

import com.online.ecommerce.common.ApiResponse;
import com.online.ecommerce.model.Category;
import com.online.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Builder used for the whole method including ApiResponse
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().success(true).message("a new category created").build());
    }

    @GetMapping("/showAll")
    List<Category> showAllCategories(){
        return categoryService.showAllCategories();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable ("id") int id, @RequestBody Category category){
        if(!categoryService.findById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().success(false).message("Category Id not present").build());
        }
        categoryService.update(id,category);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().success(true).message("Category Updated").build());
    }
}
