package com.epam.adok.core.dao;

import com.epam.adok.core.entity.BaseEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GenericDao<T extends BaseEntity> implements Dao<T> {

    @PersistenceContext(unitName = "mySqlPU")
    private EntityManager entityManager;

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public T read(int id) {
        return null;
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) {

    }
}
