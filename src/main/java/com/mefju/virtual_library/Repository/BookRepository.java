package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "SELECT * FROM book WHERE Nazwa like %?%",nativeQuery = true)
    List<Book>findBooksByNameLike(String name);
    @Query(value = "SELECT * FROM book WHERE autor like %?%",nativeQuery = true)
    List<Book>findBooksByAutorLike(String Autor);
    @Query(value = "SELECT * FROM book WHERE wydawca like %?%",nativeQuery = true)
    List<Book>findBooksByWydawcaLike(String Wydawca);
    @Query(value = "SELECT * FROM book order by Cena ",nativeQuery = true)
    List<Book>SortPrice();
    @Query(value = "SELECT * FROM book order by Cena DESC",nativeQuery = true)
    List<Book>SortPricemalejaco();
    @Query(value = "SELECT * FROM book order by Nazwa ASC ",nativeQuery = true)
    List<Book> sortnameASC();
    @Query(value = "SELECT * FROM book order by Nazwa DESC ",nativeQuery = true)
    List<Book> sortnameDESC();
    @Query(value = "SELECT * FROM book order by Popularnosc DESC ",nativeQuery = true)
    List<Book> popular();
    @Query(value = "SELECT DISTINCT Rodzaj FROM book",nativeQuery = true)
    List<String>Typeall();
    @Query(value = "SELECT * FROM book WHERE Rodzaj like ?",nativeQuery = true)
    List<Book>findBooksByTypeLike(String name);
    @Query(value = "SELECT * FROM book WHERE Cena between :min and :max ",nativeQuery = true)
    List<Book>findBooksByPrice(@Param("min") int min, @Param("max")int max);
    @Query(value = "SELECT * FROM book JOIN magazyn ON book.id = magazyn.idksiazki WHERE magazyn.idbiblioteki = ?1", nativeQuery = true)
    List<Book> FindBooksByLibrary(String id);

    @Query(value = "SELECT * FROM book WHERE dostepnosc=?", nativeQuery = true)
    List<Book> findBooksByDostepnoscIs(String tekst);


}
