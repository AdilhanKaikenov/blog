package com.epam.adok.core.dao;

import com.epam.adok.core.entity.BaseEntity;

public interface Dao<T  extends BaseEntity> {

    void save(T t);

    T read(int id);

    void update(T t);

    void delete(T t);

}
