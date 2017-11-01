package com.epam.adok.core.dao;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.QBlog;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;

import javax.ejb.Stateless;

@Stateless
public class BlogDao extends GenericDao<Blog> {


    @Override
    public Blog read(int id) {
        JPAQuery query = getJpaQuery();
        QBlog qBlog = getQBlog();
        return query.from(qBlog).where(qBlog.id.eq(id)).uniqueResult(qBlog);
    }

    @Override
    public void update(Blog blog) {
        QBlog qBlog = getQBlog();
        JPAUpdateClause updateClause = new JPAUpdateClause(getEntityManager(), qBlog);
        updateClause.where(qBlog.id.eq(blog.getId()))
                .set(qBlog.title, blog.getTitle())
                .set(qBlog.content, blog.getContent())
                .set(qBlog.author, blog.getAuthor())
                .set(qBlog.publicationDate, blog.getPublicationDate())
                .execute();
    }

    private QBlog getQBlog() {
        return QBlog.blog;
    }
}
