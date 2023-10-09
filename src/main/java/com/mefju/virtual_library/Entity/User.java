package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username",unique = true)
    private String Username;
    @Column(name = "password")
    private String Password;
    @Column(name = "enabled")
    private Boolean Enabled;
   /* @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> roles;*/
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

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.Enabled = enabled;
    }
}
