/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadisback.Service;

import com.mycompany.memeparadisback.Exception.InvalidCredentialsException;
import com.mycompany.memeparadisback.Model.User;

/**
 *
 * @author krist
 */
public class userService {
    User u = new User();
    
    public String addNewUser(User user){
    
        String result = "";
        
        if( ! (u.checkEmailUnique(user.getEmail()))){
            //it runs if the email is taken
            result = "Email is already taken!";
        }
        
        else if(user.getName().length() < 5){
            result = "firstname must be at least 3 characters";   
        }
        else{
           
            try{
                User.validatePassword(user.getPassword());
                }
            catch(Exception ex){
                result = ex.getMessage();
            }
        }
        return result;
}
    public User login(String username,String Password) throws InvalidCredentialsException{
        User user = u;
        if(user == null || !user.getPassword().equals(Password)){
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return user;
    }
}
