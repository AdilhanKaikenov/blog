package com.epam.adok.core.messageconsumer;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.Notification;
import com.epam.adok.core.entity.User;
import com.epam.adok.core.service.NotificationService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import java.text.MessageFormat;

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
    private NotificationService notificationService;

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

            Notification notification = notificationService.readNotificationByID(notificationID);

            User commentAuthor = notification.getUser();
            Blog blog = notification.getBlog();
            User blogAuthor = blog.getAuthor();
            String blogAuthorEmail = blogAuthor.getEmail();

            Email email = new SimpleEmail();

            try {
                email.setHostName("smtp.mail.ru");
                email.setSmtpPort(587);
                email.setSSLOnConnect(true);
                email.setSubject("Notification");
                email.setFrom("uvedomitel.blogov@bk.ru"); // blog2017
                email.setAuthentication("uvedomitel.blogov@bk.ru", "blog2017");
                email.setMsg(MessageFormat.format(
                        "User {0} left a comment on your blog {1} at {2}",
                        commentAuthor.getLogin(),
                        blog.getTitle(),
                        notification.getDate()));
                email.addTo(blogAuthorEmail);
                email.send();
            } catch (EmailException e) {
                e.printStackTrace(); // TODO
            }

            log.info("notification id : {}", notification.getId());

            log.info("Notification is received.");
        } catch (JMSException e) {
            e.printStackTrace(); // TODO:
        }
    }
}

