package com.epam.payments.db.dto;

import java.io.Serializable;

public class GameDTO implements Serializable {

    private static final long serialVersionUID = 6859201190090252999L;

    private Long id;
    private String name;
    private String website_link;
    private String description;

    public GameDTO() {
    }

    public GameDTO(Long id, String name, String website_link, String description) {
        this.id = id;
        this.name = name;
        this.website_link = website_link;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite_link() {
        return website_link;
    }

    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website_link='" + website_link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
