package com.epam.adok.core.service;

import com.epam.adok.core.dao.Dao;
import com.epam.adok.core.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private Dao<User> userDao;

    public void register(User user) {
        userDao.save(user);
    }
}
