package com.epam.adok.core.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog")
@PrimaryKeyJoinColumn(name = "blog_id", referencedColumnName = "id")
public class Blog extends UniqueIdEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "primaryKey.blog",
            cascade = CascadeType.ALL)
    private Set<BlogCategoryAssignment> blogCategoryAssignments = new HashSet<>();

    @Column(name = "publication_date")
    private Timestamp publicationDate;

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

    public Set<BlogCategoryAssignment> getBlogCategoryAssignments() {
        return blogCategoryAssignments;
    }

    public void setBlogCategoryAssignments(Set<BlogCategoryAssignment> blogCategoryAssignments) {
        this.blogCategoryAssignments = blogCategoryAssignments;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }
}
