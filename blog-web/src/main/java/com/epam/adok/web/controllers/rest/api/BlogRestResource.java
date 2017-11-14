package com.epam.adok.web.controllers.rest.api;

import com.epam.adok.core.annotation.log.Log;
import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.service.BlogService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/blog")
public class BlogRestResource {

    @EJB
    private BlogService blogService;

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response create(Blog blog) {

        if (blog.getId() != 0) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        blogService.createBlog(blog);
        return Response.ok(blog).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @GET
    public Response read(@PathParam("id") int id) {

        Blog blog = blogService.findBlogByID(id);

        if (blog != null) {
            return Response.ok(blog).build();
        }
        return Response.noContent().build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response update(Blog blog) {

        Blog targetBlog = blogService.findBlogByID(blog.getId());

        if (targetBlog == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        blogService.updateBlog(blog);
        return Response.ok(blog).build();
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") int id) {

        Blog blog = blogService.findBlogByID(id);

        if (blog != null) {
            blogService.removeBlogByID(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
