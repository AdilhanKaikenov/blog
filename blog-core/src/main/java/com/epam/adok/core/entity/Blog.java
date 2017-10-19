package com.epam.adok.core.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private User author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "blog_category_assignment",
            joinColumns = {@JoinColumn(name = "blog_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        if (id != blog.id) return false;
        if (!title.equals(blog.title)) return false;
        return content.equals(blog.content);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
}
