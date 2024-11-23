package com.example.onlinebookstore.Mapper;

import com.example.onlinebookstore.DTO.CategoriesDTO;
import com.example.onlinebookstore.Entity.Categories;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {

    public CategoriesDTO toDTO(Categories categories){
        CategoriesDTO dto = new CategoriesDTO();
        dto.setId(categories.getId());
        dto.setName(categories.getName());
        dto.setDescription(categories.getDescription());
        return dto;
    }

    public Categories toEntity(CategoriesDTO categoriesDTO){
        Categories categories = new Categories();
        categories.setId(categoriesDTO.getId());
        categories.setName(categoriesDTO.getName());
        categories.setDescription(categoriesDTO.getDescription());
        return categories;
    }
}
