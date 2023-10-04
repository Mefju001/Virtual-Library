package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Biblioteki")
public class Biblioteki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_Library")
    private String id;
    @Column(name = "lokalizacja")
    private String adres;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

}
