package com.epam.adok.core.service;

import com.epam.adok.core.dao.BlogDao;
import com.epam.adok.core.entity.Blog;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BlogService {

    @EJB
    private BlogDao blogDao;

    public Blog findBlogByID(int id) {
        return blogDao.read(id);
    }

    public void removeBlogByID(int id) {
        Blog targetBlog = new Blog();
        targetBlog.setId(id);
        blogDao.delete(targetBlog);
    }
}
