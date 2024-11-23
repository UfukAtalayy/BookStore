package com.example.onlinebookstore.Service;

import com.example.onlinebookstore.Entity.Categories;
import com.example.onlinebookstore.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository){
        this.categoriesRepository = categoriesRepository;
    }

    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }
    public Categories getCategoryById(Long id){
        return categoriesRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id:" + id));
    }

    public Categories createCategory(Categories categories){
        return categoriesRepository.save(categories);
    }

    public void deleteCategory(Long id){
        categoriesRepository.deleteById(id);
    }

    public Optional<Categories> updateCategory(Long id, Categories categoriesDetails){
        return categoriesRepository.findById(id).map(categories -> {
            categories.setName(categoriesDetails.getName());
            categories.setDescription(categoriesDetails.getDescription());
            return categoriesRepository.save(categories);
        });
    }
}
