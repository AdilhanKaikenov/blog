package com.epam.adok.core.service;

import com.epam.adok.core.dao.impl.CommentDao;
import com.epam.adok.core.entity.comment.AbstractComment;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CommentService<T extends AbstractComment> {

    @EJB
    private CommentDao<T> commentDao;

    public void submitComment(T comment) {
        commentDao.save(comment);
    }

    public T findCommentByID(int id) {
        return commentDao.read(id);
    }
}
