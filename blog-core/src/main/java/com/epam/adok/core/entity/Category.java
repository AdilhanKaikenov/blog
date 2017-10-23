package com.epam.adok.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity {

    @Column(name = "genre")
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
