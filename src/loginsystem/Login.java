/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import java.util.ArrayList;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author patri
 */
public class Login {
    private String delimiter = ",";
    private File accounts = new File("accounts.txt");
    private File badPasswords = new File("dictbadpass.txt");
    private ArrayList<Account> accountList = new ArrayList<Account>();
    private int minLength = 8; //trying to avoid hardcoding?
    
    //WORKING METHODS-----------------------------------------------------------------
    /** checks if user name and password are a valid match
     * @param user username of person signing in
     * @param password password of person signing in
     * @return if login was successful
     */
    public boolean userLogin (String user, String password) throws NoSuchAlgorithmException{
        boolean success = false;
        for (int i = 0; i < getAccountList().size(); i++){
            System.out.println("ex: " + getAccountList().get(i).getPassword());
            System.out.println("input: " + encrypt(password));
            if (user.equals(getAccountList().get(i).getUsername()) && encrypt(password).equals(getAccountList().get(i).getPassword())){
                success = true;
            }
        }
        return success;
    }
    
    /** checks if username is in use (via accountList)
     * @param user username being checked
     * @param password password being checked
     * @return -1(username taken), -2(short password), -3(not enough variety password), -4(unsafe overall password), 0 success
     */
    public int registerCheck(String user, String password){
        if (!userCheck(user)){ //USERNAME IN USE
            return -1;
        }else if (!passwordLength(password)){ //NOT ENOUGH CHARACTERS
            return -2;
        } else if (!passwordChar(password)){ //NOT ENOUGH CHARACTER VARIETY
            return -3;
        } else if (!passwordGood(password)){ //PASSWORD TOO POPULAR
            return -4;
        } else { //VALID USR/PWD
            return 0;
        }
    }
    
    public String encrypt(String password) throws NoSuchAlgorithmException{ //throws for now
        password += "!@#";//salting and peppering
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
    
    /** checks if username is in use (via accountList)
     * @param name username being checked
     * @return if username is available
     */
    public boolean userCheck(String name){ 
        boolean output = true;
        for (int i = 0; i < getAccountList().size(); i++){
            if (name.equals(getAccountList().get(i).getUsername())){
                output = false;
            }
        }
        return output;
    }
    
    /** checks if password is in database of bad passwords ("dictbadpass.txt")
     * @param password password being checked
     * @return password is good
     */
    public boolean passwordGood(String password) { 
        boolean output = true;
        String line; 
        try {
            Scanner s = new Scanner(badPasswords);
                while (s.hasNext()) { // for each line (account) in file
                    line = s.next();
                    if(password.matches(line)){
                        output = false;
                    }
                }
        } catch (IOException e){
            System.out.println("ERROR: File not found!" + e.getMessage());
        }
        return output;
    }
    
     /** checks if password has lower/upper/special characters
     * @param password password being checked
     * @return password is good
     */
    public boolean passwordChar(String password){ 
        boolean output = false;
        boolean lower = false;
        boolean upper = false;
        boolean number = false;
        boolean special = false;
        for(int i = 0; i < password.length(); i++){
            if (Character.isLowerCase(password.charAt(i))){ //any lowercase #?
                lower = true;
            }
            if (Character.isUpperCase(password.charAt(i))){ //any uppercase #?
                upper = true;
            }
            if (Character.isDigit(password.charAt(i))){ //any numbers?
                number = true;
            }
            if (!Character.isDigit(password.charAt(i))&&!Character.isLetter(password.charAt(i))){ //if its not a number or character its a special character probably
                special = true;
            }
        }
        if (lower && upper && number && special){ //at least one of all
            output = true;
        }
        return output;
    }
    
    /** checks if password is long enough
     * @param password password being checked
     * @return password is good
     */
    public boolean passwordLength(String password){ 
        boolean output = false;
        if(password.length() >= getMinLength()){
            output = true;
        }
        return output;
    }
    
    /** basic addition to accounts file and arrayList (does not actually make account object)
     * @param newAccount the account being saved to file
     */
    public void saveAccount(Account newAccount) { 
        try (PrintWriter pw = new PrintWriter(new FileWriter(accounts,true));) {
            pw.println("");
            pw.println(newAccount.formatAccount(getDelimiter())); // adds account, delimited
            pw.close();
            loadFile(getAccounts()); //restates array list
        } 
        catch (IOException e){
            System.out.println("Cannot save to file!" + e.getMessage());
        }
    }
    
    /** restates array list accountslist based off file
     * @param readFile file that is being read
     */
    public void loadFile(File readFile) { 
        String line; 
        Account tempAccount;
        setAccountList(new ArrayList<>()); // resetting accountList
        try {
            Scanner s = new Scanner(readFile);
                while (s.hasNext()) { // for each line (account) in file
                    line = s.next();
                    String[] accountRead = line.split(getDelimiter());
                    tempAccount = new Account (accountRead[0], accountRead[1], "null", 0 ); //null email placeholder, will change later
                    getAccountList().add(tempAccount); 
                }
        } catch (IOException e){
            System.out.println("ERROR: File not found!" + e.getMessage());
        }
    }

    //GET/SET-----------------------------------------------------------------

    /**
     * @return the delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * @return the accounts
     */
    public File getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(File accounts) {
        this.accounts = accounts;
    }

    /**
     * @return the accountList
     */
    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    /**
     * @param accountList the accountList to set
     */
    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * @return the minLength
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * @param minLength the minLength to set
     */
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
}
