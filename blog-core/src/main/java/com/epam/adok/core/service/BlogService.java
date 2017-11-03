package com.epam.adok.core.service;

import com.epam.adok.core.dao.BlogDao;
import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
public class BlogService {

    private static final Logger log = LoggerFactory.getLogger(BlogService.class);

    @EJB
    private BlogDao blogDao;

    public void createBlog(Blog blog) {
        blogDao.save(blog);
    }

    public Blog findBlogByID(int id) {
        return blogDao.read(id);
    }

    public void updateBlog(Blog blog) {
        blogDao.update(blog);
    }

    public void removeBlogByID(int id) {
        Blog targetBlog = new Blog();
        targetBlog.setId(id);
        blogDao.delete(targetBlog);
    }

    public List<Blog> findAllBlogsByParameters(Set<Category> categories, Date from, Date to) {
        log.info("Entering findAllBlogsByParameters() method.");
        return blogDao.readByParameters(categories, from, to);
    }
}
