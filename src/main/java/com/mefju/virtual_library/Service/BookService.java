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

    private BookRepository bookRepository;

    @Autowired
    public void SetBookRepository(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }
    @Transactional
    public void save(Book book)
    {
    bookRepository.save(book);
    }
    public List<Book>FindAll()
    {
        return bookRepository.findAll();
    }
    public List<Book>Zapowiedzi()
    {
        return bookRepository.findBooksByDostepnoscIs("Nie");
    }
    public List<String>TypeAll()
    {
        return bookRepository.Typeall();
    }
    public Optional<Book> FindByID(int id)
    {
        return bookRepository.findById(id);
    }
    @Transactional
    public void DeleteById(int id)
    {
       bookRepository.deleteById(id);
    }
    public List<Book>FindBookByName(String name)
    {
        return bookRepository.findBooksByNameLike(name);
    }
    public List<Book>FindBookByWydawca(String wydawca)
    {
        return bookRepository.findBooksByWydawcaLike(wydawca);
    }
    public List<Book>FindBookByAutor(String Autor)
    {
        return bookRepository.findBooksByAutorLike(Autor);
    }
    public List<Book>FindBookByPrice(int min,int max)
    {
        return bookRepository.findBooksByPrice(min, max);
    }
    public List<Book>FindBookByType(String type)
    {
        return bookRepository.findBooksByTypeLike(type);
    }
    public List<Book>FindBookByLibrary(String Biblioteka)
    {
        return bookRepository.FindBooksByLibrary(Biblioteka);
    }
    public List<Book>SortPriceASC()
    {
        return bookRepository.SortPrice();
    }
    public List<Book>SortPriceDSC()
    {
        return bookRepository.SortPricemalejaco();
    }
    public List<Book>SortNameASC()
    {
        return bookRepository.sortnameASC();
    }
    public List<Book>SortNameDESC()
    {
        return bookRepository.sortnameDESC();
    }
    public List<Book>SortByPopular()
    {
        return bookRepository.popular();
    }

}
