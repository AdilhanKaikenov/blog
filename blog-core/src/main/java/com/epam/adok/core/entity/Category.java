package com.epam.adok.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "genre")
    private String genre;

    @Column(name = "added_date")
    private Timestamp addedDate;

    @OneToMany(mappedBy = "primaryKey.category",
            cascade = CascadeType.ALL)
    private Set<BlogCategoryAssignment> blogCategoryAssignments = new HashSet<>();

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

    public Set<BlogCategoryAssignment> getBlogCategoryAssignments() {
        return blogCategoryAssignments;
    }

    public void setBlogCategoryAssignments(Set<BlogCategoryAssignment> blogCategoryAssignments) {
        this.blogCategoryAssignments = blogCategoryAssignments;
    }
}
