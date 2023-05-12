/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Controller;

import com.mycompany.memeparadis.Configuration.Database;
import com.mycompany.memeparadis.Model.Content;
import com.mycompany.memeparadis.Model.Tags;
import com.mycompany.memeparadis.Model.User;
import com.mycompany.memeparadis.Service.ContentService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Path("getHowManyContent")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getHowManyContent() throws Exception{
        Integer result = conts.getHowManyContent();
        return result;
    }
    @GET
    @Path("GetMostLikedPosts")
    @Produces(MediaType.APPLICATION_JSON)
    public Content GetMostLikedPosts() throws Exception{
        Content result = conts.GetMostLikedPosts();
        return result;
    }
    @GET
    @Path("getEnglishContents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Content> getEnglishContents() throws Exception{
     List<Content> result = conts.getEnglishContents();
    return result;
    }
    @GET
    @Path("getHungarianContents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Content> getHungarianContents () throws Exception{
     List<Content> result = conts.getHungarianContents();
    return result;
    }
    @GET
    @Path("getPictures")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Content> getPictures () throws Exception{
     List<Content> result = conts.getPictures();
    return result;
    }
    @GET
    @Path("getVideos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Content> getVideos () throws Exception{
     List<Content> result = conts.getVideos();
    return result;
    }
    @GET
    @Path("getAllContentRand")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Content> getAllContentRand () throws Exception{
     List<Content> result = conts.getAllContentRand();
    return result;
    }
    @POST
    @Path("updateTag")
    public String updateTag(Tags tag ){
      String result = "";
            try{
                result = conts.updateTag(tag.getTag(),tag.getId());
            }catch(Exception ex){
            System.err.println(""+ex.getMessage());
            }
            return result;
    }
//  @DELETE
//@Path("deleteContent")
//@Consumes(MediaType.APPLICATION_JSON)
//public String deleteContent(User u){
//  String result = "";  
//  try{
//    result = conts.deleteContent(u.getId(), u.getCurrentPw());
//  } catch(Exception ex){
//      System.err.println("" + ex.getMessage());
//      result = ex.getMessage();
//  }
//  return result;
//}
@GET
@Path("getContentBytag/{tagId}")
@Produces(MediaType.APPLICATION_JSON)
public Response getContentByTag(@PathParam("tagId") String tagId) {
    try {
        List<Content> contentList = ContentService.getContentByTag(tagId);
        if (contentList != null && !contentList.isEmpty()) {
            return Response.ok(contentList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    } catch (Exception e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
}

    
    
