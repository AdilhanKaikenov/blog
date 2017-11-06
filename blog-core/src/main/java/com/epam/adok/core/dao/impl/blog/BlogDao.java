package com.epam.adok.core.dao.impl.blog;

import com.epam.adok.core.dao.GenericDao;
import com.epam.adok.core.dao.impl.querybuilder.BlogQueryBuilder;
import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.QBlog;
import com.mysema.query.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BlogDao extends GenericDao<Blog> {

    private static final Logger log = LoggerFactory.getLogger(BlogDao.class);

    @Override
    public Blog read(int id) {
        JPAQuery query = getJpaQuery();
        QBlog qBlog = getQBlog();
        return query.from(qBlog).where(qBlog.id.eq(id)).uniqueResult(qBlog);
    }

    private QBlog getQBlog() {
        return QBlog.blog;
    }

    public List<Blog> readByParameters(BlogFilter filter) {
        log.info("Entering readByParameters() method.");
        BlogQueryBuilder blogQueryBuilder = new BlogQueryBuilder(getEntityManager(), filter);
        JPAQuery jpaQuery = blogQueryBuilder.createJPAQuery();
        log.info("Leaving readByParameters() method.");
        return jpaQuery.list(getQBlog());
    }
}
