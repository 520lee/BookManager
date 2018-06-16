package com.jcut.vo;

import java.math.BigDecimal;

public class BookDetailVo {

    private String bookId;

    private String title;

    private String originTitle;

    private String author;

    private String authorInfo;

    private String publish;

    private BigDecimal price;

    private String summary;

    private String status;

    private String sort;

    private String coverUrl;

    private String isbn;

    private String pubdate;

    private Integer pages;

    private String binding;

    private String average;

    private String numraters;

    public BookDetailVo(String bookId, String title, String originTitle, String author, String authorInfo, String publish, BigDecimal price, String summary, String status, String sort, String coverUrl, String isbn, String pubdate, Integer pages, String binding, String average, String numraters) {
        this.bookId = bookId;
        this.title = title;
        this.originTitle = originTitle;
        this.author = author;
        this.authorInfo = authorInfo;
        this.publish = publish;
        this.price = price;
        this.summary = summary;
        this.status = status;
        this.sort = sort;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
        this.pubdate = pubdate;
        this.pages = pages;
        this.binding = binding;
        this.average = average;
        this.numraters = numraters;
    }

    public BookDetailVo() {
        super();
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

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getNumraters() {
        return numraters;
    }

    public void setNumraters(String numraters) {
        this.numraters = numraters;
    }
}
