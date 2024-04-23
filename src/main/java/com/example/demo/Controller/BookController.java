package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.Service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookService bookService;
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book b) {
		return bookService.addBook(b);
	}
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable int id) {
		return  bookService.getBookById(id);
	}
	
}
