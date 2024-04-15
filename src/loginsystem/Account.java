/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
        
/**
 *
 * @author patri
 */
public class Account {
    private String username;
    private String password;
    private String email;
    private int accountId;
    private int age;   
    static private int accounts = 0;
    
    //WORKING METHODS ---------------------------------------------------------
    public Account(String usr, String pwd, String newEmail, int newAge){ //full on account
        accounts += 1;
        this.username = usr;
        this.password = pwd;
        this.email = newEmail;
        this.age = newAge;
        this.accountId = accounts;
    }
    
    public Account(){ //for temp accounts
        this.username = "john doe";
        this.password = "1234";
        this.email = "null";
        this.age = 0;
        this.accountId = 0;
    }
    
    public Account(String usr, String pwd){ //for new accounts with no info field
        this.username = usr;
        this.password = pwd;
        this.email = "null";
        this.age = 0;
        this.accountId = 0;
    }
    
    public String encrypt(String password) throws NoSuchAlgorithmException{ //throws for now
        password += "!@#";
        //java helper class to perform encryption
        MessageDigest md = MessageDigest.getInstance("MD5");
        //give the helper function the password
        md.update(password.getBytes());
        //perform the encryption
        byte byteData[] = md.digest();
        //To express the byte data as a hexadecimal number (the normal way)
        String encryptedPassword = "";
        for (int i = 0; i < byteData.length; ++i) {
        encryptedPassword += (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1,3));
        }
        return encryptedPassword ;
    }
    
    /**
     * @param delim the delimiter being used
     * @return formatted string with user and password
     */
    public String formatAccount(String delim){
        try{
            return (this.username + delim + encrypt(this.password));
               } catch (NoSuchAlgorithmException e){
                   return ("Error!");
               }
        
    } 
    
    // GETTERS, SETTERS -------------------------------------------------------
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    
}
