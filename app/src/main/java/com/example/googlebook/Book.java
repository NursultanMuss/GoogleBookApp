package com.example.googlebook;

public class Book {
    private String title;
    private String author;
    private String description;
    private String imgLink;
    private int pageCount;
    private float avrRating;
    private int ratingCount;

    public Book(String title, String author, String description, String imgLink, int pageCount, float avrRating, int ratingCount) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imgLink = imgLink;
        this.pageCount = pageCount;
        this.avrRating = avrRating;
        this.ratingCount = ratingCount;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public float getAvrRating() {
        return avrRating;
    }

    public void setAvrRating(int avrRating) {
        this.avrRating = avrRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
