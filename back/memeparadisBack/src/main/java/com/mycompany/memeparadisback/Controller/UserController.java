/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadisback.Controller;

import com.mycompany.memeparadisback.Model.User;
import com.mycompany.memeparadisback.Service.userService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("User")
public class UserController {
    userService us = new userService();

    public UserController() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    //Unique endpoints
    @POST
    @Path("addNewUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewUser(User user){
        String result = us.addNewUser(user);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    @POST
    @Path("login")
    public Response login(String username,String password){
        User result = us.login(username,password);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
}
