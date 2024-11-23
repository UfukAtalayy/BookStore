package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.DTO.CategoriesDTO;
import com.example.onlinebookstore.Entity.Categories;
import com.example.onlinebookstore.Mapper.CategoriesMapper;
import com.example.onlinebookstore.Service.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    private final CategoriesMapper categoriesMapper;

    public CategoriesController(CategoriesService categoriesService,CategoriesMapper categoriesMapper){
        this.categoriesService = categoriesService;
        this.categoriesMapper = categoriesMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> getAllCategories(){
        List<Categories> categories = categoriesService.getAllCategories();
        List<CategoriesDTO> categoriesDTOS = categories.stream()
                .map(categoriesMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoriesDTOS);
    }

    //Id ile kategori al
    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDTO> getCategoryById(@PathVariable Long id){
        Optional<Categories> categories = Optional.ofNullable(categoriesService.getCategoryById(id));
        return categories.map(value ->ResponseEntity.ok(categoriesMapper.toDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Yeni kategori oluşturma
    @PostMapping
    public ResponseEntity<CategoriesDTO> createCategory(@RequestBody CategoriesDTO categoriesDTO){
        Categories categories = categoriesMapper.toEntity(categoriesDTO);
        Categories savedCategories = categoriesService.createCategory(categories);
        return ResponseEntity.ok(categoriesMapper.toDTO(savedCategories));
    }

    //Kategori güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDTO> updateCategory(@PathVariable Long id, @RequestBody CategoriesDTO categoriesDTO){
        Optional<Categories> updatedCategories = categoriesService.updateCategory(id,categoriesMapper.toEntity(categoriesDTO));
        return updatedCategories.map(categories -> ResponseEntity.ok(categoriesMapper.toDTO(categories)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Kategori silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoriesService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
