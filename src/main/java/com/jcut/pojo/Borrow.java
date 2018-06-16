package com.jcut.pojo;

import java.util.Date;

public class Borrow {
    private Integer id;

    private String userId;

    private String bookId;

    private String title;

    private Date borrowTime;

    private Date expectReturnTime;

    public Borrow(Integer id, String userId, String bookId, String title, Date borrowTime, Date expectReturnTime) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.title = title;
        this.borrowTime = borrowTime;
        this.expectReturnTime = expectReturnTime;
    }

    public Borrow() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getExpectReturnTime() {
        return expectReturnTime;
    }

    public void setExpectReturnTime(Date expectReturnTime) {
        this.expectReturnTime = expectReturnTime;
    }
}