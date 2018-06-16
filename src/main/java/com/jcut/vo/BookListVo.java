package com.jcut.vo;

import java.math.BigDecimal;

public class BookListVo {
    private String bookId;

    private String name;

    private String author;

    private String publish;

    private BigDecimal price;

    private String coverUrl;

    private String average;

    public BookListVo(String bookId, String name, String author, String publish, BigDecimal price, String coverUrl, String average) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.price = price;
        this.coverUrl = coverUrl;
        this.average = average;
    }

    public BookListVo() {
        super();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoverUrl() { return coverUrl; }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
