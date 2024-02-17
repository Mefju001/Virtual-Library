package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "magazyn")
public class Magazyn {
    @Id
    @Column(name= "idmagazyn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idksiazki")
    @NotBlank
    @NotNull
    private Book Ksiazka;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idbiblioteki")
    @NotBlank
    @NotNull
    private Biblioteki Biblioteka;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getKsiazka() {
        return Ksiazka;
    }

    public void setKsiazka(Book ksiazka) {
        Ksiazka = ksiazka;
    }

    public Biblioteki getBiblioteka() {
        return Biblioteka;
    }

    public void setBiblioteka(Biblioteki biblioteka) {
        Biblioteka = biblioteka;
    }
}
