/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author patri
 */
public class Searching {
    public static int binarySearch (String term, String[] list) { //returns the index or -1
        int low = 0;
        int high = list.length;
        int mid = 0;
        while (low <= high) {
            if(list[mid].equals(term)){
                return mid;
            } 
            else if (list[mid].compareTo(term)>0){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return -1;
    }
    public static int seqSearch(String term, String[] list) {//returns the index or -1
        int index = -1;
        for(int i = 0; i < list.length; i++ ){
            if(list[i].equals(term)){
                index = i;
            }
        }
        return index;
    }
}
