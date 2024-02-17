package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Biblioteki")
public class Biblioteki {
    @Id
    @Column(name= "ID_Library",unique = true)
    private String id;
    @Column(name = "lokalizacja")
    @NotBlank
    @NotNull
    private String lokalizacja;
    @Column(name = "Mapa")
    private String mapa;
    @Column(name = "Adres")
    @NotBlank
    @NotNull
    private String Adres;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }
}
