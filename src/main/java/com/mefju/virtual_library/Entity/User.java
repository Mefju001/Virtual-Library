package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users",schema =  "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Username")
    private String Username;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Enabled")
    private int Enabled;

    public User() {
    }
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

    public int getEnabled() {
        return Enabled;
    }

    public void setEnabled(int enabled) {
        this.Enabled = enabled;
    }
}
