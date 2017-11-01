package com.epam.adok.core.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@XmlRootElement
public class Category extends UniqueIdEntity {

    @Column(name = "genre")
    private String genre;

    @Column(name = "added_date")
    private Timestamp addedDate;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

}
