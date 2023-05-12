/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Controller;

import com.mycompany.memeparadis.Model.Content;
import com.mycompany.memeparadis.Model.ContentTag;
import com.mycompany.memeparadis.Model.Tags;
import com.mycompany.memeparadis.Service.ContentTagService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
public Response createContent_tag(Tags content){
    String result = contss.createContentTag(content.getTagsId(), content.getContentId());
    return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
}

}
