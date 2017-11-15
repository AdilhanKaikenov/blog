package com.epam.adok.core.dao.impl;

import com.epam.adok.core.dao.GenericDao;
import com.epam.adok.core.entity.Notification;

import javax.ejb.Stateless;

@Stateless
public class NotificationDao extends GenericDao<Notification> {

    @Override
    public Notification read(int id) {
        return getEntityManager().find(Notification.class, id);
    }
}
