/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Service;

import com.mycompany.memeparadis.Configuration.Database;
import com.mycompany.memeparadis.Model.Content;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
   }
  