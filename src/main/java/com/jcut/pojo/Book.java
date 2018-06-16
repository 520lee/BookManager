package com.jcut.pojo;

import java.math.BigDecimal;

public class Book {
    private Integer id;

    private String bookId;

    private String title;

    private String originTitle;

    private String author;

    private String authorInfo;

    private String publish;

    private BigDecimal price;

    private String summary;

    private Integer status;

    private Integer sortId;

    private String coverUrl;

    private String isbn;

    private String pubdate;

    private Integer pages;

    private String binding;

    private String average;

    private String numraters;

    public Book(Integer id, String bookId, String title, String originTitle, String author, String authorInfo, String publish, BigDecimal price, String summary, Integer status, Integer sortId, String coverUrl, String isbn, String pubdate, Integer pages, String binding, String average, String numraters) {
        this.id = id;
        this.bookId = bookId;
        this.title = title;
        this.originTitle = originTitle;
        this.author = author;
        this.authorInfo = authorInfo;
        this.publish = publish;
        this.price = price;
        this.summary = summary;
        this.status = status;
        this.sortId = sortId;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
        this.pubdate = pubdate;
        this.pages = pages;
        this.binding = binding;
        this.average = average;
        this.numraters = numraters;
    }

    public Book() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle == null ? null : originTitle.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo == null ? null : authorInfo.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
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
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate == null ? null : pubdate.trim();
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
        this.binding = binding == null ? null : binding.trim();
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average == null ? null : average.trim();
    }

    public String getNumraters() {
        return numraters;
    }

    public void setNumraters(String numraters) {
        this.numraters = numraters == null ? null : numraters.trim();
    }
}