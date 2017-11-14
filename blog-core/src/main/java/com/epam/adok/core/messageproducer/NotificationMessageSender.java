package com.epam.adok.core.messageproducer;

import com.epam.adok.core.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class NotificationMessageSender {

    private static final Logger log = LoggerFactory.getLogger(NotificationMessageSender.class);

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/queue/NotificationsQueue")
    private Queue notificationsQueue;

    public void sendNotificationMessage(Notification notification) {
        log.info("Entering sendNotificationMessage() method...");
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(notificationsQueue);
            connection.start();
            ObjectMessage notificationMessage = session.createObjectMessage(notification);
            messageProducer.send(notificationMessage);
            log.info("The message was successfully sent to the NotificationsQueue.");
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace(); // TODO:
        }
    }
}
