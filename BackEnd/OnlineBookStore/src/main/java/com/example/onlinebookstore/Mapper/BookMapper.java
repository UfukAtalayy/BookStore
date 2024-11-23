package com.example.onlinebookstore.Mapper;

import com.example.onlinebookstore.DTO.BookDTO;
import com.example.onlinebookstore.Entity.Book;
import com.example.onlinebookstore.Entity.Categories;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book){
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setDescription(book.getDescription());
        dto.setPrice(book.getPrice());
        dto.setStockQuantity(book.getStockQuantity());
        dto.setCategoryId(book.getCategories().getId());
        return dto;
    }

    public Book toEntity(BookDTO bookDTO, Categories categories){
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setStockQuantity(bookDTO.getStockQuantity());
        book.setCategories(categories);
        return book;
    }
}
