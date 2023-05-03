package com.mycompany.memeparadis.Service;

import com.mycompany.memeparadis.Model.Content;
import com.mycompany.memeparadis.Model.ContentTag;
import com.mycompany.memeparadis.Model.Tags;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author krist
 */
public class ContentTagService {
 ContentTag contTS = new ContentTag();
 
public String createContentTag(Integer tag, Integer content) {
    String result = ""; 
    try {
        result = contTS.createContent_tag(tag, content);
    } catch (Exception ex) {
        result = ex.getMessage();
    } 
    return result;
}

}
