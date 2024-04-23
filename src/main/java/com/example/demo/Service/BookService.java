package com.example.demo.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	BookRepo bookRepo;
	@Autowired
	private RestTemplate restTemplate;
	private String downStreamURL;
	
	
	public BookService(RestTemplate restTemplate,@Value("${downstream.url}") String downStreamURL) {
		super();
		this.restTemplate = restTemplate;
		this.downStreamURL = downStreamURL;
	}


	public Book addBook(Book b) {
		ResponseEntity<Book> response = restTemplate.postForEntity(downStreamURL, b, Book.class);
		return response.getBody();
	}
	

	
	public Book getBookById(int id) {
		String url = downStreamURL + "/" + id; 
	    ResponseEntity<Book[]> response = restTemplate.getForEntity(url, Book[].class);
	    List<Book> list = Arrays.asList(response.getBody());
	    for (Book book : list) {
			if(book.getId()==id) {
				return  book;
			}
		}
		return null;
	 
	}

}
