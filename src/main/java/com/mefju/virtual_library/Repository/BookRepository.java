package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT * FROM book WHERE name=?",nativeQuery = true)
    List<Book>findBooksByNameLike(String name);
    @Query(value = "SELECT * FROM book order by price ",nativeQuery = true)
    List<Book>SortPrice();
    @Query(value = "SELECT * FROM book order by price DESC",nativeQuery = true)
    List<Book>SortPricemalejaco();
    @Query(value = "SELECT * FROM book order by Popularity DESC ",nativeQuery = true)
    List<Book>popular();
    @Query(value = "SELECT DISTINCT type FROM book",nativeQuery = true)
    List<String>Typeall();
    @Query(value = "SELECT * FROM book WHERE type=?",nativeQuery = true)
    List<Book>findBooksByTypeLike(String name);
}
