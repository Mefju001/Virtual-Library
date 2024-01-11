package com.mefju.virtual_library;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VirtualLibraryBookServiceTests {
    // Testy dla BookService
    @Autowired
    private BookService bookService;
    @Test
    void TestFindAllBooks()
    {
        List<Book> result = bookService.FindAll();
        System.out.println(result);
        assertNotNull(result);
    }
    @Test
    void TestFindByID()
    {
        Optional<Book> result = bookService.FindByID(1);
        System.out.println(result);
        assertTrue(result.isPresent()); // Upewnij się, że wynik nie jest pusty
        assertEquals(1, result.get().getId()); // Sprawdź czy ID zwróconej książki to 1 (lub inne oczekiwane ID)
        assertNotNull(result.get().getName()); // Sprawdź czy tytuł nie jest nullem
    }
    @Test
    void TestFindByName()
    {
        List <Book> result = bookService.FindBookByName("Wampirze Cesarstwo");
        System.out.println(result);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Wampirze Cesarstwo", result.get(0).getName());
    }
}
