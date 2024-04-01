/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author patri
 */
public class Login {
    private String inputUser;
    private String inputPassword;
    private String delimiter = ",";
    private File accounts = new File("accounts");
    private ArrayList<Account> accountList = new ArrayList<Account>();
    
    //testing??
    public static void main(String[] args) throws IOException { 
        File account = new File("accounts");
        Account me = new Account("patrick","12345","someone@gmail.com",18);
    }
    
    /**
     * @param name username being checked
     * @return if username already exists
     */
    public boolean userCheck(String name){ 
        boolean output = false;
        for (int i = 0; i < accountList.size(); i++){
            if (accountList.get(i).getUsername() == name){
                output = false;
            }
        }
        return output;
    }
    
    /** basic addition to accounts file and arrayList (does not actually make account object)
     * @param newAccount the account being saved to file
     */
    public void saveUser(Account newAccount) throws IOException { 
        try (PrintWriter pw = new PrintWriter(accounts)) {
            pw.println(newAccount.getUsername() + delimiter + newAccount.getUsername());
            pw.close();
        }
    }
    
    /** add account to array list
     * @param newAccount the account being saved to array list
     */
    public void loadUsers(Account newAccount) throws IOException { 
        accountList.add(newAccount); 
    }
}
