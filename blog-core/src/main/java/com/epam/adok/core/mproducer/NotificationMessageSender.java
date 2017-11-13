package com.epam.adok.core.mproducer;

import com.epam.adok.core.entity.Notification;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class NotificationMessageSender {

    @Resource(mappedName =  "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/queue/NotificationsQueue")
    private Queue notificationsQueue;

    public void sendNotificationMessage(Notification notification) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(notificationsQueue);
            connection.start();
            ObjectMessage notificationMessage = session.createObjectMessage(notification);
            messageProducer.send(notificationMessage);
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace(); // TODO:
        }
    }
}
