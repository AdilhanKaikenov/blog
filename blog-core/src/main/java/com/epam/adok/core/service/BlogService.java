package com.epam.adok.core.service;

import com.epam.adok.core.dao.impl.blog.BlogDao;
import com.epam.adok.core.dao.impl.blog.BlogFilter;
import com.epam.adok.core.entity.Blog;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BlogService {

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

    public List<Blog> findAllBlogsByParameters(BlogFilter filter) {
        return blogDao.readByParameters(filter);
    }
}
