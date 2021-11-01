package edu.deadshot.hibernatesearch.controller;

import edu.deadshot.hibernatesearch.entity.Book;
import edu.deadshot.hibernatesearch.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/setup")
    @ResponseStatus(HttpStatus.OK)
    public void indexBook() throws InterruptedException {
        bookRepository.luceneIndexing();
    }

    @PostMapping(value = "/search")
    public List<Book> getBookByName(@RequestBody String name) {
        return bookRepository.searchBookByName(name);
    }

}
