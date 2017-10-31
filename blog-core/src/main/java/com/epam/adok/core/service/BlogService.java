package com.epam.adok.core.service;

import com.epam.adok.core.dao.Dao;
import com.epam.adok.core.entity.Blog;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BlogService {

    @Inject
    private Dao<Blog> blogDao;
}
