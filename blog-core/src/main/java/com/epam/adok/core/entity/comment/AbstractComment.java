package com.epam.adok.core.entity.comment;

import com.epam.adok.core.entity.AbstractBaseEntity;
import com.epam.adok.core.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "AbstractComment.readById", query = "select comment from AbstractComment comment where comment.id = :id")
})
@Table(name = "comment")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "comment_type")
public abstract class AbstractComment extends AbstractBaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "comment_date")
    private Date commentDate;

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

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
