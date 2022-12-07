package com.example.mybookstore;

public class Booklist {
    String author,book_Name,edition,publication;

    public Booklist() {

    }

    public Booklist(String author, String book_Name, String edition, String publication) {
        this.author = author;
        this.book_Name = book_Name;
        this.edition = edition;
        this.publication = publication;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public String getEdition() {
        return edition;
    }

    public String getPublication() {
        return publication;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }
}
