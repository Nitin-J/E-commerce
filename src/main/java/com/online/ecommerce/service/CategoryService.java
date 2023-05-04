package com.online.ecommerce.service;

import com.online.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    void saveCategory(Category category);
    List<Category> showAllCategories();
    void update(int id, Category updateCategory);
    boolean findById(int id);
}
