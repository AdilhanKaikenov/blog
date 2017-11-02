package com.epam.adok.core.dao;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.QBlog;
import com.mysema.query.jpa.impl.JPAQuery;

import javax.ejb.Stateless;

@Stateless
public class BlogDao extends GenericDao<Blog> {

    @Override
    public Blog read(int id) {
        JPAQuery query = getJpaQuery();
        QBlog qBlog = getQBlog();
        return query.from(qBlog).where(qBlog.id.eq(id)).uniqueResult(qBlog);
    }

    private QBlog getQBlog() {
        return QBlog.blog;
    }
}
