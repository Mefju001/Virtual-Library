package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Biblioteki", schema = "public")
public class Biblioteki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Library")
    private String id;
    @Column(name = "Nazwa_Book")
    private int ID_Book;
    @Column(name = "Adres")
    private String adres;
    @Column(name = "Nazwa")
    private String Nazwa;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getID_Book() {
        return ID_Book;
    }

    public void setID_Book(int ID_Book) {
        this.ID_Book = ID_Book;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }
}
