package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username",unique = true)
    private String Username;
    @Column(name = "password")
    @NotBlank
    @NotNull
    private String Password;
    @Column(name = "enabled")
    @NotBlank
    @NotNull
    private Boolean Enabled;
    @Column(name = "Imie")
    @NotBlank
    @NotNull    private String Imie;
    @Column(name = "Nazwisko")
    @NotBlank
    @NotNull
    private String Nazwisko;


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.Enabled = enabled;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }
}
