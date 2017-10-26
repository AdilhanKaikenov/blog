package com.epam.adok.core.entity.comment;

import com.epam.adok.core.entity.AbstractEntity;
import com.epam.adok.core.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "comment_type")
public abstract class AbstractComment extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "comment_date")
    private Timestamp commentDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }
}
