/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Controller;

import com.mycompany.memeparadis.Model.Content;
import com.mycompany.memeparadis.Model.Tags;
import com.mycompany.memeparadis.Service.ContentTagService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author krist
 */
@Path("ContentTag")
public class ContentTagController {
   ContentTagService contss = new ContentTagService();   
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
@Path("createContent_tag")
@Consumes(MediaType.APPLICATION_JSON)
public String createContent_tag(Tags tag,Content content){
    String result = contss.createContentTag(tag, content);
    return result;
}
}
