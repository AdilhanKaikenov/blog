package com.epam.adok.web.controllers.rest.api;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.service.BlogService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blog")
public class BlogRestResource {

    @EJB
    private BlogService blogService;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @GET
    public Response getBlog(@PathParam("id") int id) {

        Blog blog = blogService.findBlogByID(id);

        if (blog != null) {
            return Response.ok(blog).build();
        }
        return Response.noContent().build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteBlog(@PathParam("id") int id) {

        Blog blog = blogService.findBlogByID(id);

        if (blog != null) {
            blogService.removeBlogByID(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
