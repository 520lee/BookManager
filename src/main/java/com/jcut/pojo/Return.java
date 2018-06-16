package com.jcut.pojo;

import java.util.Date;

public class Return {
    private Integer id;

    private String userId;

    private String bookId;

    private String title;

    private Date borrowTime;

    private Date returnTime;

    public Return(Integer id, String userId, String bookId, String title, Date borrowTime, Date returnTime) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.title = title;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }

    public Return() {
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

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}