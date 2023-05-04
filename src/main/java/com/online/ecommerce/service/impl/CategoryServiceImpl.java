package com.online.ecommerce.service.impl;

import com.online.ecommerce.model.Category;
import com.online.ecommerce.repo.CategoryRepo;
import com.online.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public List<Category> showAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public void update(int id, Category updateCategory) {
        Category category = categoryRepo.findById(id).get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepo.save(category);
    }

    @Override
    public boolean findById(int id) {
        return categoryRepo.findById(id).isPresent();
    }
}
