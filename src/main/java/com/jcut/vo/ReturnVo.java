package com.jcut.vo;

import java.util.Date;

public class ReturnVo {

    private String bookId;

    private String title;

    private String author;

    private String publish;

    private String price;

    private String status;

    private String borrowTime;

    private String returnTime;

    public ReturnVo() {
        super();
    }

    public ReturnVo(String bookId, String title, String author, String publish, String price, String status, String borrowTime, String returnTime) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.price = price;
        this.status = status;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
