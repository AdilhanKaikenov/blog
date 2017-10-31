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
        QBlog blog = QBlog.blog;
        return query.from(blog).where(blog.id.eq(id)).uniqueResult(blog);
    }

    @Override
    public void update(Blog blog) {

    }
}
