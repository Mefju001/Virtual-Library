package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "SELECT * FROM book WHERE Nazwa=?",nativeQuery = true)
    List<Book>findBooksByNameLike(String name);
    @Query(value = "SELECT * FROM book order by Cena ",nativeQuery = true)
    List<Book>SortPrice();
    @Query(value = "SELECT * FROM book order by Cena DESC",nativeQuery = true)
    List<Book>SortPricemalejaco();
    @Query(value = "SELECT * FROM book order by Popularnosc DESC ",nativeQuery = true)
    List<Book> popular();
    @Query(value = "SELECT DISTINCT Rodzaj FROM book",nativeQuery = true)
    List<String>Typeall();
    @Query(value = "SELECT * FROM book WHERE Rodzaj=?",nativeQuery = true)
    List<Book>findBooksByTypeLike(String name);
    @Query(value = "SELECT * FROM book WHERE Cena between :min and :max ",nativeQuery = true)
    List<Book>findBooksByPrice(@Param("min") int min, @Param("max")int max);

    @Query(value = "select * from book where ID_library =? ",nativeQuery = true)
    List<Book> FindBooksByLibrary(String id);

}
