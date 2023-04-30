package com.mycompany.memeparadis.Service;

import com.mycompany.memeparadis.Model.Tags;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author krist
 */
public class TagsService {
    Tags t = new Tags();
    
    public String createTag(Tags tag){
        String result = "";
        try{
            result = t.createTag(tag);
        }catch(Exception ex){
            result = ex.getMessage();
        }
        return result;
    }
}
