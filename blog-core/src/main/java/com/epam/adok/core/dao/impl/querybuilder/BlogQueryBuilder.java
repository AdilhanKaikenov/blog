package com.epam.adok.core.dao.impl.querybuilder;

import com.epam.adok.core.dao.impl.blog.BlogFilter;
import com.epam.adok.core.dao.impl.blog.BlogPredicates;
import com.epam.adok.core.entity.Category;
import com.epam.adok.core.entity.QBlog;
import com.epam.adok.core.util.DateRange;
import com.epam.adok.core.util.QueryDslUtil;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Set;


public class BlogQueryBuilder {

    private static final Logger log = LoggerFactory.getLogger(BlogQueryBuilder.class);

    private BlogFilter filter;
    private EntityManager entityManager;
    private QBlog blog;

    public BlogQueryBuilder(EntityManager entityManager, BlogFilter filter) {
        this.filter = filter;
        this.entityManager = entityManager;
        this.blog = QBlog.blog;
    }

    public JPAQuery createJPAQuery() {
        log.info("Entering createJPAQuery() method.");
        final JPAQuery query = new JPAQuery(entityManager);
        query.from(blog);
        BooleanExpression predicate = handleFilterParameters(filter);
        log.info("Leaving createJPAQuery... ");
        return query.where(predicate);
    }

    private BooleanExpression handleFilterParameters(BlogFilter filter) {
        log.info("Entering handleFilterParameters() method.");

        Set<Category> categories = filter.getCategories();

        DateRange dateRange = filter.getDateRange();
        Date from = dateRange.getFrom();
        Date to = dateRange.getTo();

        BooleanExpression predicate = null;

        if (categories != null && !categories.isEmpty()) {
            for (Category category : categories) {
                predicate = QueryDslUtil.and(predicate, BlogPredicates.isBlogHasCategory(blog, category));
            }
        }

        if (from != null) {
            predicate = QueryDslUtil.and(predicate, BlogPredicates.isPublishedDateAfter(blog, from));
        }

        if (to != null) {
            predicate = QueryDslUtil.and(predicate, BlogPredicates.isPublishedDateBefore(blog, to));
        }
        log.info("Leaving handleFilterParameters... ");
        return predicate;
    }
}
