package com.epam.adok.web.controllers.rest.api;

import com.epam.adok.core.entity.comment.BlogComment;
import com.epam.adok.core.entity.comment.CategoryComment;
import com.epam.adok.core.service.CommentService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentRestResource {

    @EJB
    private CommentService<BlogComment> blogCommentService;

    @EJB
    private CommentService<CategoryComment> categoryCommentService;

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
}
