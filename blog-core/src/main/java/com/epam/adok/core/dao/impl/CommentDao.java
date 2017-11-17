package com.epam.adok.core.dao.impl;

import com.epam.adok.core.dao.GenericDao;
import com.epam.adok.core.entity.comment.AbstractComment;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class CommentDao<T extends AbstractComment> extends GenericDao<T> {

    @Override
    public T read(int id) {
        Query query = getEntityManager().createNamedQuery("AbstractComment.readById");
        query.setParameter("id", id);
        return (T) query.getSingleResult();
    }

    @Override
    protected Query getReadAllNamedQuery() {
        return getEntityManager().createNamedQuery("AbstractComment.readAll");
    }
}
