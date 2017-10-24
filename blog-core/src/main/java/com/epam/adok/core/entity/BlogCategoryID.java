package com.epam.adok.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BlogCategoryID implements Serializable {

    private Blog blog;

    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
