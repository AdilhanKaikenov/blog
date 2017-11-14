package com.epam.adok.core.messageconsumer;

import com.epam.adok.core.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

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

    @PersistenceContext(unitName = "mySqlPU")
    private EntityManager entityManager;

    @Override
    public void onMessage(Message message) {
        log.info("Entering onMessage() method...");
        try {
            if (!(message instanceof ObjectMessage)) {
                log.error("Message should be instance of ObjectMessage class");
                return;
            }
            ObjectMessage objectMessage = (ObjectMessage) message;
            Serializable object = objectMessage.getObject();
            if (!(object instanceof Notification)) {
                log.error("Not expected instance in message");
                return;
            }

            Notification notification = (Notification) object;

            entityManager.persist(notification);
            log.info("Notification is received.");
        } catch (JMSException e) {
            e.printStackTrace(); // TODO:
        }
    }
}

