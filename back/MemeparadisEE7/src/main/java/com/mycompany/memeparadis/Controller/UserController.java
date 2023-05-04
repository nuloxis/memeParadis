/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Controller;

import com.mycompany.memeparadis.Model.User;
import com.mycompany.memeparadis.Service.userService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String encryptString(String input) throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("MD5");
        
        byte[] messageDigest=md.digest(input.getBytes());
        BigInteger bigInt =new BigInteger(1,messageDigest);
        return bigInt.toString(16);
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
    
    @POST
    @Path("addNewUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewUser(User user){
        String result = us.addNewUser(user);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user){
        User result;
        try {
            result = us.login(user.getEmail(),encryptString(user.getPassword()));
        } catch (Exception ex) {
            System.err.println("");
            result = new User();
        }
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    @PUT
    @Path("updateUserName")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUserName(String username,Integer id){
        String result = "";
        try{
            result = us.updateUserName(username, id);
        }catch(Exception ex){
        System.err.println(""+ex.getMessage());
        }
        return result;
    }
}
