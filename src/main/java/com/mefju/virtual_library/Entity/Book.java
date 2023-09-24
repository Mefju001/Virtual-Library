package com.mefju.virtual_library.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book", schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID")
    private int id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Type")
    private String Type;
    @Column(name = "Price")
    private double Price;
    @Column(name = "Publisher")
    private String Publisher;
    @Column(name = "Rating")
    private int Rating;
    @Column(name = "Author")
    private String Author;
    @Column(name = "Popularnosc")
    private int Popularnosc;
    @Column(name = "Dostepnosc")
    private String Dostepnosc;
    @Column(name = "Picturepath")
    private String PicturePath;

    public Book() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public double getPrice() {
        return Price;
    }
    public void setPrice(double price) {
        Price = price;
    }
    public String getPublisher() {
        return Publisher;
    }
    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
    public int getRating() {
        return Rating;
    }
    public void setRating(int rating) {
        Rating = rating;
    }
    public String getAuthor() {
        return Author;
    }
    public void setAuthor(String author) {
        Author = author;
    }
    public int getPopularnosc() {
        return Popularnosc;
    }
    public void setPopularnosc(int popularnosc) {
        Popularnosc = popularnosc;
    }
    public String getPicturePath() {
        return PicturePath;
    }
    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }
    public String getDostepnosc() {
        return Dostepnosc;
    }
    public void setDostepnosc(String dostepnosc) {
        Dostepnosc = dostepnosc;
    }
}
