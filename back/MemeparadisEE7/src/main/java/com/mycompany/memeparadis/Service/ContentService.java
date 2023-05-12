/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Service;

import com.google.protobuf.ServiceException;
import com.mycompany.memeparadis.Configuration.Database;
import com.mycompany.memeparadis.Model.Content;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


/**
 *
 * @author krist
 */
public class ContentService {
 Content c = new Content();
   
   public String createContent(Content content){
       String result = "";
       try{
           result = c.createContent(content);
             
       }catch(Exception ex){
           result = ex.getMessage();
       }
       return result;
   }
 
   public Integer getHowManyContent() throws Exception{
       Integer result2 = 0;
       try{
           result2 = c.getHowManyContent();
       }catch(Exception ex){
         System.out.println(ex.getMessage());
        throw new Exception(""+ex.getMessage());
       }
       return result2;
   }
   public Content GetMostLikedPosts() throws Exception{
       Content result3 = null;
       try{
           result3 = c.GetMostLikedPosts();
       }catch(Exception ex){
           System.out.println(ex.getMessage());
           throw new Exception(""+ex.getMessage());
       }
       return result3;
   }
   public List<Content> getEnglishContents() throws Exception{
    List<Content> result = null;
    try {
      result = c.getEnglishContents();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    }
    return result;
   }
   public List<Content> getHungarianContents() throws Exception {
    List<Content> result = null;
    try {
      result = c.getHungarianContents();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    }
    return result;
   }
   public List<Content> getPictures() throws Exception {
    List<Content> result = null;
    try {
      result = c.getPictures();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    }
    return result;
   }
   public List<Content> getVideos() throws Exception {
    List<Content> result = null;
    try {
      result = c.getVideos();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    }
    return result;
   }
   public List<Content> getAllContentRand() throws Exception {
    List<Content> result = null;
    try {
      result = c.getAllContentRand();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    }
    return result;
   }
   public String updateTag(String tag,Integer id){
    String result = "";
        try{
            c.updateTag(tag, id);
            result = "Sikeres frissítés";
        }catch(Exception ex){
        result = ex.getMessage();
        } 
        return result;
   }
public static List<Content> getContentByTag(String tagId) throws ServiceException {
    try {
        return Content.getContentBytag(tagId);
    } catch (Exception e) {
        throw new ServiceException("Error retrieving content by tag: " + e.getMessage(), e);
    }
}
}

//   public String deleteContent(Integer id,String currentPw){
//    String result = "";
//    try{
//      String content  =  c.deleteContent(id, currentPw);
//      result = content;
//    }catch(Exception ex){
//    result = ex.getMessage();
//    }
//    return result;
//    }
//}

  