package com.example.onlinebookstore.Service;

import com.example.onlinebookstore.DTO.BookDTO;
import com.example.onlinebookstore.Entity.Book;
import com.example.onlinebookstore.Entity.Categories;
import com.example.onlinebookstore.Mapper.BookMapper;
import com.example.onlinebookstore.Repository.BookRepository;
import com.example.onlinebookstore.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private BookMapper bookMapper;


    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id:"));
        return bookMapper.toDTO(book);
    }

    public BookDTO createBook(BookDTO bookDTO){
        Categories categories = categoriesRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Book book = bookMapper.toEntity(bookDTO,categories);
        return bookMapper.toDTO(bookRepository.save(book));

    }

    public BookDTO updateBook(Long id, BookDTO dto){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setStockQuantity(dto.getStockQuantity());

        Categories categories = categoriesRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not fond"));
        book.setCategories(categories);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }


}
