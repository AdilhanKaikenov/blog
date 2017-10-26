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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogCategoryID that = (BlogCategoryID) o;

        if (blog != null ? !blog.equals(that.blog) : that.blog != null) return false;
        return category != null ? category.equals(that.category) : that.category == null;
    }

    @Override
    public int hashCode() {
        int result = blog != null ? blog.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
