package com.epam.adok.core.dao;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.Category;
import com.epam.adok.core.entity.QBlog;
import com.mysema.query.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
public class BlogDao extends GenericDao<Blog> {

    private static final Logger log = LoggerFactory.getLogger(BlogDao.class);

    @Override
    public Blog read(int id) {
        JPAQuery query = getJpaQuery();
        QBlog qBlog = getQBlog();
        return query.from(qBlog).where(qBlog.id.eq(id)).uniqueResult(qBlog);
    }

    public List<Blog> readByParameters(Set<Category> categories, Date from, Date to) {
        log.info("Entering readByParameters() method.");

        JPAQuery query = getJpaQuery();
        QBlog qBlog = getQBlog();

        query.from(qBlog);

        if (categories != null && !categories.isEmpty()) {
            for (Category category : categories) {
                if (category.getId() != 0) {
                    query.where(qBlog.categories.contains(category));
                }
            }
        }
        if (from != null) {
            query.where(qBlog.publicationDate.after(new Timestamp(from.getTime())));
        }
        if (to != null) {
            query.where(qBlog.publicationDate.before(new Timestamp(to.getTime())));
        }
        log.info("Leaving readByParameters() method.");
        return query.list(qBlog);
    }

    private QBlog getQBlog() {
        return QBlog.blog;
    }
}
