/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import java.io.File;
        
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
    public Account(String usr, String pwd, String newEmail, int newAge){
        accounts += 1;
        this.username = usr;
        this.password = pwd;
        this.email = newEmail;
        this.age = newAge;
        this.accountId = accounts;
    }
    
    public boolean checkUser(){ 
        return true;
    }
    
    public boolean checkPassword(){ 
        return true;
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
