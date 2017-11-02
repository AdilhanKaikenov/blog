package com.epam.adok.core.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@Entity
@Table(name = "blog_category_assignment")
@XmlRootElement
public class BlogCategoryAssignment {

    @EmbeddedId
    private BlogCategoryID pk;

    @MapsId("blogID")
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    @ManyToOne
    private Blog blog;

    @MapsId("categoryID")
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private Category category;

    @Column(name = "date")
    private Timestamp date;

    public BlogCategoryID getPk() {
        return pk;
    }

    public void setPk(BlogCategoryID pk) {
        this.pk = pk;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogCategoryAssignment that = (BlogCategoryAssignment) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;
        if (blog != null ? !blog.equals(that.blog) : that.blog != null) return false;
        return category != null ? category.equals(that.category) : that.category == null;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
