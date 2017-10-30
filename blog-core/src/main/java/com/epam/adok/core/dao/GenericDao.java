package com.epam.adok.core.dao;

import com.epam.adok.core.entity.BaseEntity;
import com.epam.adok.core.entity.QBaseEntity;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GenericDao<T extends BaseEntity> implements Dao<T> {

    @PersistenceContext(unitName = "mySqlPU")
    private EntityManager entityManager;

    private JPAQuery getJpaQuery() {
        return new JPAQuery(entityManager);
    }

    private QBaseEntity getBaseEntity() {
        return QBaseEntity.baseEntity;
    }

    @Override
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    public T read(int id) {
        JPAQuery query = getJpaQuery();
        QBaseEntity baseEntity = getBaseEntity();
        return (T) query.from(baseEntity).where(baseEntity.id.eq(id)).uniqueResult(baseEntity);
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) {
        QBaseEntity baseEntity = getBaseEntity();
        JPADeleteClause deleteClause = new JPADeleteClause(entityManager, baseEntity);
        deleteClause.where(baseEntity.id.eq(t.getId())).execute();
    }
}
