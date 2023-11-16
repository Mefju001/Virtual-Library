package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Biblioteki")
public class Biblioteki {
    @Id
    @Column(name= "ID_Library",unique = true)
    private String id;
    @Column(name = "lokalizacja")
    @NotNull()
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
