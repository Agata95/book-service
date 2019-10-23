package j25.cloud.cloud.repository;

import j25.cloud.cloud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleOrAndAuthor(String title, String author);
//    List<Book> findAllByAuthorLikeAndTitleLike(String title, String author);

}
