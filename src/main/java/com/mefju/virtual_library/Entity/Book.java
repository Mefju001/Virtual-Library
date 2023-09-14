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
    @Column(name = "Stock")
    private int Stock;
    @Column(name = "Popularity")
    private int Popularity;
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
    public int getStock() {
        return Stock;
    }
    public void setStock(int stock) {
        Stock = stock;
    }
    public int getPopularity() {
        return Popularity;
    }
    public void setPopularity(int popularity) {
        Popularity = popularity;
    }
    public String getPicturePath() {
        return PicturePath;
    }
    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }
}
