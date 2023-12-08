package com.mefju.virtual_library.Service;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Repository.BibliotekiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotekiService {

    private BibliotekiRepository bibliotekiRepository;

    @Autowired
    public void SetBibliotekiRepository(BibliotekiRepository bibliotekiRepository)
    {
        this.bibliotekiRepository = bibliotekiRepository;
    }

    @Transactional
    public List<Biblioteki> FindAll()
    {
        return bibliotekiRepository.findAll();
    }

}
