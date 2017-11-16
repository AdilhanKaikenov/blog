package com.epam.adok.core.messageproducer;

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

    public void sendNotificationMessage(int notificationID) {
        log.info("Entering sendNotificationMessage() method...");
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(notificationsQueue);
            connection.start();
            StreamMessage streamMessage = session.createStreamMessage();
            streamMessage.setIntProperty("notificationID", notificationID);
            messageProducer.send(streamMessage);
            log.info("The message was successfully sent to the NotificationsQueue.");
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace(); // TODO:
        }
    }
}
