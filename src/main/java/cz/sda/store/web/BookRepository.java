package cz.sda.store.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByLanguage(String lang);
    List<Book> findAllByLanguageAndYear(String lang, Integer year);

    @Query("SELECT b FROM Book b WHERE b.category.id = :id")
    List<Book> findByCategoryWithQuery(Long id);
}
