package com.example.googlebook;

public class Book {
    private String title;
    private String subTitle;
    private String author;
    private String description;
    private String imgLink;
    private int pageCount;
    private int avrRating;

    public Book(String title, String subTitle, String author, String description, String imgLink, int pageCount, int avrRating) {
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.description = description;
        this.imgLink = imgLink;
        this.pageCount = pageCount;
        this.avrRating = avrRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public int getAvrRating() {
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
