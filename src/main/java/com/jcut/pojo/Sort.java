package com.jcut.pojo;

public class Sort {
    private Integer id;

    private Integer sortId;

    private String name;

    public Sort(Integer id, Integer sortId, String name) {
        this.id = id;
        this.sortId = sortId;
        this.name = name;
    }

    public Sort() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}