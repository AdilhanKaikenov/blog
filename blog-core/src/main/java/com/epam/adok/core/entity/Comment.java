package com.epam.adok.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment extends AbstractEntity {

    @Column(name = "blog_id")
    private Blog blog;

    @Column(name = "parent_comment_id")
    private Comment parentComment;

    @Column(name = "user_id")
    private User user;

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
}
