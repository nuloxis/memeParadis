/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Service;

import com.mycompany.memeparadis.Exception.InvalidCredentialsException;
import com.mycompany.memeparadis.Model.User;

/**
 *
 * @author krist
 */
public class userService {
    User u = new User();
    
    public String addNewUser(User user){
    
        String result = "";
        
        if( !(u.checkEmailUnique(user.getEmail()))){
            result = "Email is already taken!";
        }
        
        else if(user.getName().length() < 5){
            result = "firstname must be at least 5 characters";   
        }
        else{
           
            try{
               User.validatePassword(user.getPassword()); result = u.addNewUser(user);
                }
            catch(Exception ex){
                result = ex.getMessage();
            }
        }
        return result;
}
    public User login(String email,String password) throws InvalidCredentialsException{
       Integer id = User.login(email,password);
        User user = u.getUserById(id);
        if(user == null){
            throw new InvalidCredentialsException("Invalid email or password");
        }
       return user;
    }
}
