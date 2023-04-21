/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Controller;

import com.mycompany.memeparadis.Configuration.Database;
import com.mycompany.memeparadis.Model.Content;
import com.mycompany.memeparadis.Service.ContentService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("Content")
public class ContentController {
    ContentService conts = new ContentService();

    public ContentController() {
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
     @Path("createContent")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createContent(Content content){
        String result = conts.createContent(content);
        return result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllContent")
    public Response getAllContent() {
        List<Content> resultList;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
            EntityManager em = emf.createEntityManager();

            resultList = em.createQuery("SELECT c FROM Content c", Content.class).getResultList();

            em.clear();
            em.close();
            emf.close();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
        return Response.ok(resultList).build();
    }
}
