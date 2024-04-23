package com.example.demo.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        book.setName("Sample Book");
        book.setAuthor("Sample Author");
        book.setDescription("This is a sample book description.");
        book.setRating(4.5);

        when(bookService.addBook(any())).thenReturn(book);

        mockMvc.perform(post("/books/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Sample Book\", \"author\": \"Sample Author\", \"description\": \"This is a sample book description.\", \"rating\": 4.5 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Book"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setName("Sample Book");
        book.setAuthor("Sample Author");
        book.setDescription("This is a sample book description.");
        book.setRating(4.5);

        when(bookService.getBookById(1)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Book"));
    }
}
