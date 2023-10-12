package com.mefju.virtual_library.Service;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void save(Book book)
    {
    bookRepository.save(book);
    }
    @Transactional
    public List<Book>FindAll()
    {
        return bookRepository.findAll();
    }
    @Transactional
    public List<String>TypeAll()
    {
        return bookRepository.Typeall();
    }
    @Transactional
    public Optional<Book> FindByID(int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            // Dodatkowa logika, jeśli książka o danym ID została znaleziona
            Book book = optionalBook.get();
            // Możesz tutaj wykonać dodatkowe operacje lub logikę biznesową

            return optionalBook;
        } else {
            // Dodatkowa logika, jeśli książka o danym ID nie została znaleziona
            // Na przykład, możesz rzucić wyjątek lub podjąć inne działania

            return Optional.empty();
        }
    }
    @Transactional
    public void DeleteById(int id)
    {
       bookRepository.deleteById(id);
    }
    @Transactional
    public List<Book>FindBookByName(String name)
    {
        return bookRepository.findBooksByNameLike(name);
    }
    @Transactional
    public List<Book>FindBookByPrice(int min,int max)
    {
        return bookRepository.findBooksByPrice(min, max);
    }
    @Transactional
    public List<Book>FindBookByType(String type)
    {
        return bookRepository.findBooksByTypeLike(type);
    }
    @Transactional
    public List<Book>FindBookByLibrary(String Biblioteka)
    {
        return bookRepository.FindBooksByLibrary(Biblioteka);
    }
    @Transactional
    public List<Book>SortPriceASC()
    {
        return bookRepository.SortPrice();
    }
    @Transactional
    public List<Book>SortPriceDSC()
    {
        return bookRepository.SortPricemalejaco();
    }
    @Transactional
    public List<Book>SortByPopular()
    {
        return bookRepository.popular();
    }

}
