package com.epam.adok.web.controllers.rest.api;

import com.epam.adok.core.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/user")
public class UserRestResource {

    @EJB
    private UserService userService;

    @GET
    @Produces("text/plain")
    public String testMethod() {
        return "testMethod() is called";
    }
}
