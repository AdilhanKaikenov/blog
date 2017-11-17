package com.epam.adok.core.service;

import com.epam.adok.core.annotation.log.Log;
import com.epam.adok.core.dao.impl.NotificationDao;
import com.epam.adok.core.entity.Notification;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Log
@Stateless
public class NotificationService {

    @EJB
    private NotificationDao notificationDao;

    public Notification readNotificationByID(int id) {
        return notificationDao.read(id);
    }

    public void deleteAllNotifocationsByCreatedOnBefore(Date expiryDate) {
        notificationDao.deleteByCreatedOnBefore(expiryDate);
    }
}
