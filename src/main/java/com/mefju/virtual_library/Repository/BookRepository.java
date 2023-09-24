package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT * FROM book order by Popularnosc DESC ",nativeQuery = true)
    List<Book> popular();
    @Query(value = "SELECT DISTINCT type FROM book",nativeQuery = true)
    List<String>Typeall();
    @Query(value = "SELECT * FROM book WHERE type=?",nativeQuery = true)
    List<Book>findBooksByTypeLike(String name);
    @Query(value = "SELECT * FROM book WHERE price between :min and :max ",nativeQuery = true)
    List<Book>findBooksByPrice(@Param("min") int min, @Param("max")int max);
    @Query(value = "SELECT * FROM book where promocja = true",nativeQuery = true)
    List<Book>findBooksByPromocja();
    @Query(value = "select * from book inner join Biblioteki b on book.name = Nazwa_Book",nativeQuery = true)
    List<Book>FindBooksByLibrary();
}
