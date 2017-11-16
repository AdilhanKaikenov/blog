package com.epam.adok.core.messageconsumer;

import com.epam.adok.core.dao.impl.NotificationDao;
import com.epam.adok.core.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;

@MessageDriven(
        name = "NotificationMessageReceiver",
        description = "MDBean receives messages from Notifications Queue",
        mappedName = "java:/jms/queue/NotificationsQueue",
        messageListenerInterface = MessageListener.class,
        activationConfig =
                {
                        @ActivationConfigProperty(
                                propertyName = "destination",
                                propertyValue = "java:/jms/queue/NotificationsQueue"
                        ),
                        @ActivationConfigProperty(
                                propertyName = "destinationType",
                                propertyValue = "javax.jms.Queue"
                        )}
)
public class NotificationMessageReceiver implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationMessageReceiver.class);

    @EJB
    private NotificationDao notificationDao;

    @Override
    public void onMessage(Message message) {
        log.info("Entering onMessage() method...");
        try {
            if (!(message instanceof StreamMessage)) {
                log.error("Message should be instance of StreamMessage class");
                return;
            }
            StreamMessage streamMessage = (StreamMessage) message;
            int notificationID = streamMessage.getIntProperty("notificationID");

            Notification notification = notificationDao.read(notificationID);

            log.info("notification id : {}", notification.getId());

            log.info("Notification is received.");
        } catch (JMSException e) {
            e.printStackTrace(); // TODO:
        }
    }
}

