package com.epam.adok.core.service;

import com.epam.adok.core.dao.Dao;
import com.epam.adok.core.entity.comment.AbstractComment;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CommentService {

    @Inject
    private Dao<AbstractComment> commentDao;

}
