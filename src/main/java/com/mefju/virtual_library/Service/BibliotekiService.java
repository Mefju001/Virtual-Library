package com.mefju.virtual_library.Service;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Entity.Magazyn;
import com.mefju.virtual_library.Repository.BibliotekiRepository;
import com.mefju.virtual_library.Repository.MagazynRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotekiService {

    private final BibliotekiRepository bibliotekiRepository;
    private final MagazynRepository magazynRepository;


    @Autowired
    public BibliotekiService(BibliotekiRepository bibliotekiRepository, MagazynRepository magazynRepository) {

        this.bibliotekiRepository = bibliotekiRepository;
        this.magazynRepository = magazynRepository;
    }

    public List<Biblioteki> FindAll()
    {
        return bibliotekiRepository.findAll();
    }
    public Optional<Biblioteki> FindByID(String id)
    {
        return bibliotekiRepository.findById(id);
    }
    public List<Magazyn> FindById(int id1, String id2)
    {
        return magazynRepository.FindByID(id1,id2);
    }
    @Transactional
    public void dodajDoMagazynu(Book idKsiazki, Biblioteki idBiblioteki)
    {
        Magazyn magazyn = new Magazyn();
        magazyn.setKsiazka(idKsiazki);
        magazyn.setBiblioteka(idBiblioteki);
        magazynRepository.save(magazyn);
    }
    public Optional<Biblioteki> FindById(String id)
    {
        return bibliotekiRepository.findById(id);
    }
    @Transactional
    public void delete(String id)
    {
        bibliotekiRepository.deleteById(id);
    }
    @Transactional
    public void save(Biblioteki biblioteki) {
        bibliotekiRepository.save(biblioteki);
    }
}
