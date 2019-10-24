package j25.cloud.cloud.controller;

import j25.cloud.cloud.model.Book;
import j25.cloud.cloud.model.dto.CreateBookRequest;
import j25.cloud.cloud.model.dto.UpdateBookRequest;
import j25.cloud.cloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/book/")
public class BookController {
    @Autowired
    private BookService bookService;

//    można to zrobić bez dto -> w repo Pawła
    @PutMapping
    public Long putBook(@RequestBody CreateBookRequest bookRequest) {

        return bookService.save(bookRequest);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postGrade(@RequestBody UpdateBookRequest book) {
        bookService.update(book);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long bookId) {
        return bookService.getById(bookId);
    }

    @GetMapping("/search")
    public List<Book> getByTitleAndAuthor(@RequestParam(name = "author", required = false) String author,
                                          @RequestParam(name = "title", required = false) String title) {
        return bookService.getByAuthorOrTitle(author, title);
    }
}
