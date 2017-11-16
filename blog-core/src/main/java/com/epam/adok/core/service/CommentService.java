package com.epam.adok.core.service;

import com.epam.adok.core.annotation.log.Log;
import com.epam.adok.core.dao.impl.CommentDao;
import com.epam.adok.core.dao.impl.NotificationDao;
import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.Notification;
import com.epam.adok.core.entity.User;
import com.epam.adok.core.entity.comment.AbstractComment;
import com.epam.adok.core.entity.comment.BlogComment;
import com.epam.adok.core.messageproducer.NotificationMessageSender;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.Date;

@Log
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommentService<T extends AbstractComment> {

    @EJB
    private CommentDao<T> commentDao;

    @EJB
    private NotificationMessageSender messageSender;

    @EJB
    private NotificationDao notificationDao;

    public void submitComment(T comment) {
        commentDao.save(comment);
        if (comment instanceof BlogComment) {
            Notification notification = createNotification((BlogComment) comment);
            notificationDao.save(notification);
            messageSender.sendNotificationMessage(notification.getId());
        }
    }

    public T findCommentByID(int id) {
        return commentDao.read(id);
    }

    private Notification createNotification(BlogComment comment) {
        Notification notification = new Notification();
        User commentAuthor = comment.getUser();
        Blog commentBlog = comment.getBlog();
        Date date = new Date();
        notification.setUser(commentAuthor);
        notification.setBlog(commentBlog);
        notification.setDate(date);
        return notification;
    }
}
