package com.epam.adok.core.mconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(
        name = "NotificationMessageReceiver",
        description = "MDBean receives messages from Notifications Queue",
        mappedName = "queue/NotificationsQueue",
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

    @PersistenceContext(unitName = "mySqlPU")
    private EntityManager entityManager;

    @Override
    public void onMessage(Message message) {

    }
}
