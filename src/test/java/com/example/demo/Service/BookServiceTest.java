package com.example.demo.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BookServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setId(1);
        book.setName("Sample Book");
        book.setAuthor("Sample Author");
        book.setDescription("This is a sample book description.");
        book.setRating(4.5);

        ResponseEntity<Book> responseEntity = new ResponseEntity<>(book, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(Book.class), eq(Book.class))).thenReturn(responseEntity);

        //Book result = bookService.addBook(book);
        //assertEquals(book, result);
    }
    

    @Test
    public void testGetBookById() {
        int id = 1;
        Book book = new Book();
        book.setId(id);
        book.setName("Sample Book");
        book.setAuthor("Sample Author");
        book.setDescription("This is a sample book description.");
        book.setRating(4.5);

        Book[] books = {book};
        ResponseEntity<Book[]> responseEntity = new ResponseEntity<>(books, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(Book[].class))).thenReturn(responseEntity);

        Book result = bookService.getBookById(id);
        assertEquals(book, result);
    }

    @Test
    public void testGetBookByIdNotFound() {
        int id = 2;

        Book book = new Book();
        book.setId(1);
        book.setName("Sample Book");
        book.setAuthor("Sample Author");
        book.setDescription("This is a sample book description.");
        book.setRating(4.5);

        Book[] books = {book};
        ResponseEntity<Book[]> responseEntity = new ResponseEntity<>(books, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(Book[].class))).thenReturn(responseEntity);

        Book result = bookService.getBookById(id);
        assertEquals(null, result);
    }
}