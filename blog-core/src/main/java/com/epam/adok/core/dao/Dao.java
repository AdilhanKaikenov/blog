package com.epam.adok.core.dao;

import com.epam.adok.core.entity.AbstractBaseEntity;

public interface Dao<T  extends AbstractBaseEntity> {

    void save(T t);

    T read(int id);

    void update(T t);

    void delete(T t);

}
