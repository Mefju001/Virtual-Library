package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "authorities")
public class Role {
    @Id
    @Column(name = "username",unique = true)
    @NotBlank
    @NotNull
    private String ID;

    @Column(name = "authority")
    @NotBlank
    @NotNull
    private String rola;



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola.toUpperCase();
    }
}
