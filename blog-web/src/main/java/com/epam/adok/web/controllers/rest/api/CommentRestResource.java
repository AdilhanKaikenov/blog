package com.epam.adok.web.controllers.rest.api;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.Notification;
import com.epam.adok.core.entity.User;
import com.epam.adok.core.entity.comment.BlogComment;
import com.epam.adok.core.entity.comment.CategoryComment;
import com.epam.adok.core.messageproducer.NotificationMessageSender;
import com.epam.adok.core.service.CommentService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.Date;

@Path("/comment")
public class CommentRestResource {

    @EJB
    private CommentService<BlogComment> blogCommentService;

    @EJB
    private CommentService<CategoryComment> categoryCommentService;

    @EJB
    private NotificationMessageSender messageSender;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/blog/{id}")
    @GET
    public Response readBlogComment(@PathParam("id") int id) {

        BlogComment comment = blogCommentService.findCommentByID(id);

        if (comment != null) {
            return Response.ok(comment).build();
        }
        return Response.noContent().build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{id}")
    @GET
    public Response readCategoryComment(@PathParam("id") int id) {

        CategoryComment comment = categoryCommentService.findCommentByID(id);

        if (comment != null) {
            return Response.ok(comment).build();
        }
        return Response.noContent().build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/blog")
    public Response create(BlogComment comment) {

        if (comment.getId() != 0) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        blogCommentService.submitComment(comment);

        Notification notification = createNotification(comment);

        messageSender.sendNotificationMessage(notification);

        return Response.ok(comment).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/category")
    public Response create(CategoryComment comment) {

        if (comment.getId() != 0) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        categoryCommentService.submitComment(comment);
        return Response.ok(comment).build();
    }

    private Notification createNotification(BlogComment comment) {
        Notification notification = new Notification();
        User commentAuthor = comment.getUser();
        Blog commentBlog = comment.getBlog();
        Date date = new Date();
        String message = MessageFormat.format(
                "User {0} left a comment under your blog {1} on {2}",
                commentAuthor.getLogin(),
                commentBlog.getTitle(),
                date
        );
        notification.setUser(commentAuthor);
        notification.setBlog(commentBlog);
        notification.setDate(date);
        notification.setText(message);
        return notification;
    }
}
