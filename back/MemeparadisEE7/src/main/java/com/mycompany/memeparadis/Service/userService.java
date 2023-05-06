/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Service;

import com.mycompany.memeparadis.Exception.InvalidCredentialsException;
import com.mycompany.memeparadis.Model.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 * @author krist
 */
public class userService {
    User u = new User();
    
    public String encryptString(String input) throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("MD5");
        
        byte[] messageDigest=md.digest(input.getBytes());
        BigInteger bigInt =new BigInteger(1,messageDigest);
        return bigInt.toString(16);
    }
    
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
    public User login(String email,String password) throws InvalidCredentialsException, Exception{
       Integer id = User.login(email,password);
        User user = u.getUserByID(id);
//        if(user == null){
//            throw new InvalidCredentialsException("Invalid email or password"+id);
//        }
       return user;
    }
    public String updateUserName(String username,Integer id){
    String result = "";
        try{
            u.updateUserName(username, id);
            result = "Sikeres frissítés";
        }catch(Exception ex){
        result = ex.getMessage();
        } 
        return result;
    }
    public String updateEmail(String email,Integer id){
    String result = "";
        try{
            u.updateEmail(email, id);
            result = "Sikeres frissítés";
        }catch(Exception ex){
        result = ex.getMessage();
        } 
        return result;
    }
    public String updatePassword(String currentPW,String newPw,String email){
    String result = "";
        try{

            result = u.updatePassword(currentPW,newPw, email);
            
        }catch(Exception ex){
        result = ex.getMessage();
        } 
        return result;
    }
    public String updateBirthDate(Date date,Integer id){
    String result = "";
        try{
            u.updateBirthDate(date, id);
            result = "Sikeres frissítés";
        }catch(Exception ex){
        result = ex.getMessage();
        } 
        return result;
    }
}
