package com.epam.adok.core.dao.impl;

import com.epam.adok.core.dao.GenericDao;
import com.epam.adok.core.entity.Notification;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Date;

@Stateless
public class NotificationDao extends GenericDao<Notification> {

    @Override
    public Notification read(int id) {
        return getEntityManager().find(Notification.class, id);
    }

    public void deleteByCreatedOnBefore(Date expiryDate) {
        Query query = getEntityManager().createNamedQuery("Notification.removeByCreatedOnBefore");
        query.setParameter("expiryDate", expiryDate);
        query.executeUpdate();
    }

    @Override
    protected Query getReadAllNamedQuery() {
        return getEntityManager().createNamedQuery("Notification.readAll");
    }
}
