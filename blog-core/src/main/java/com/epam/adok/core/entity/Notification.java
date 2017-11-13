package com.epam.adok.core.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification extends AbstractBaseEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date date;

    public Notification(User user, Blog blog, String text, Date date) {
        this.user = user;
        this.blog = blog;
        this.text = text;
        this.date = date;
    }

    public Notification() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

