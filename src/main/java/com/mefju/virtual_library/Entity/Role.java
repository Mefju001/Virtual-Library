package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Username")
    private String ID;

    @Column(name = "Authority")
    private String rola;

    public Role() {
    }

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
        this.rola = "ROLE_" + rola.toUpperCase();
    }
}
