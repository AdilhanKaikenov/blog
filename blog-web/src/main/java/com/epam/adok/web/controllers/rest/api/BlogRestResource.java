package com.epam.adok.web.controllers.rest.api;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.service.BlogService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/blog")
public class BlogRestResource {

    @EJB
    private BlogService blogService;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @GET
    public Blog getBlog(@PathParam("id") int id){
        return blogService.findBlogByID(id);
    }
}
