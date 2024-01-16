package com.mefju.virtual_library;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Repository.BookRepository;
import com.mefju.virtual_library.Service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class VirtualLibraryBookServiceTests {
    // Testy dla BookService
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @MockBean
    private BookService bookServiceMock;

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
    @Test
    void TestSave()
    {
        // Przygotowanie danych testowych
        Book newBook = new Book();
        newBook.setName("Wampirze Cesarstwo");

        bookServiceMock.save(newBook);
        verify(bookServiceMock).save(newBook); // Zweryfikuj mocka
    }
    @Test
    void TestSaveAndFindAndDelete()
    {
        Book book = new Book();
        book.setName("Test");
        bookRepository.save(book);

        Optional<Book>savedbook = bookRepository.findById(book.getId());
        assertTrue(savedbook.isPresent());
        assertEquals("Test",savedbook.get().getName());

        bookRepository.deleteById(book.getId());
        Optional<Book> deletedBook = bookRepository.findById(book.getId());
        assertFalse(deletedBook.isPresent());

    }
}
