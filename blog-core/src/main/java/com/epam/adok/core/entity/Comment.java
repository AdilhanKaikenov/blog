package com.epam.adok.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "blog_id")
    private Blog blog;

    @Column(name = "parent_comment_id")
    private Comment parentComment;

    @Column(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (blog != null ? !blog.equals(comment.blog) : comment.blog != null) return false;
        if (parentComment != null ? !parentComment.equals(comment.parentComment) : comment.parentComment != null)
            return false;
        return user != null ? user.equals(comment.user) : comment.user == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        result = 31 * result + (parentComment != null ? parentComment.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
