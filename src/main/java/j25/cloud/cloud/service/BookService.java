package j25.cloud.cloud.service;

import j25.cloud.cloud.mapper.BookMapper;
import j25.cloud.cloud.model.Book;
import j25.cloud.cloud.model.dto.CreateBookRequest;
import j25.cloud.cloud.model.dto.UpdateBookRequest;
import j25.cloud.cloud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public Long save(CreateBookRequest dto) {
        Book book = bookMapper.createBookFromDto(dto);
        book.setYearWritten(LocalDate.now().getYear());

        return bookRepository.save(book).getId();
    }

    public void update(UpdateBookRequest bookToEdit) {
        Optional<Book> bookOptional = bookRepository.findById(bookToEdit.getBookId());
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if (bookToEdit.getNumberOfAvailableCopies() != 0) {
                book.setNumberOfAvailableCopies(bookToEdit.getNumberOfAvailableCopies());
            }

            bookRepository.save(book);
            return;
        }
        throw new EntityNotFoundException("book, id: " + bookToEdit.getBookId());
    }


    public Book getById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }
        throw new EntityNotFoundException("book, id: " + bookId);
    }

//    szukanie po autorze i/lub tytule
    public List<Book> getByAuthorOrTitle(String author, String title) {
        if (author != null && !author.isEmpty() && title != null && !title.isEmpty()) {
            return bookRepository.findByAuthorContainingOrTitleContaining(author, title);
        }
        if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorContaining(author);
        }
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleLike(title);
        }
        return new ArrayList<>();
    }
}
