/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author patri
 */
public class Main {
    //testing??
    
    public static void main(String[] args) throws IOException { 
        File accounts = new File("accounts.txt");
        Account me = new Account("patrick","12345","someone@gmail.com",18);
        Login login = new Login();
        login.saveAccount(me);
        login.loadFile(accounts);
        for (int i = 0; i < login.getAccountList().size(); i++){
            System.out.println(login.getAccountList().get(i).getUsername());
        }
        System.out.println(login.userCheck("patrick"));
        System.out.println(login.registerCheck("parick", "12345678"));
    }
}
