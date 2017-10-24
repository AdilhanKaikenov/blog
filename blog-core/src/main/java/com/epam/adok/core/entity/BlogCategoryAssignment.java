package com.epam.adok.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blog_category_assignment")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.blog",
                joinColumns = @JoinColumn(name = "blog_id")),
        @AssociationOverride(name = "primaryKey.category",
                joinColumns = @JoinColumn(name = "category_id")) })
public class BlogCategoryAssignment {

    private BlogCategoryID primaryKey = new BlogCategoryID();

    @Column(name = "date")
    private Timestamp date;

    @EmbeddedId
    public BlogCategoryID getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(BlogCategoryID primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
