package com.epam.adok.core.dao;

import com.epam.adok.core.entity.BaseEntity;
import com.mysema.query.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDao<T extends BaseEntity> implements Dao<T> {

    @PersistenceContext(unitName = "mySqlPU")
    private EntityManager entityManager;

    public JPAQuery getJpaQuery() {
        return new JPAQuery(entityManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
    }
}
