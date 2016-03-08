/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc141_river;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author ty
 */
public class RiverCrossing {
    static ArrayList<String> list = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, 
            UnsupportedEncodingException 
    {
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter writer = new PrintWriter("mp2.out", "UTF-8");
        
        while(in.hasNext()){
            list.add(in.nextLine());
        }
        
        
        
        for(int i=0; i < list.size(); i++){//Iterate every line
            ArrayList left = new ArrayList();
            left.add("M");
            left.add("L");
            left.add("R");
            left.add("C");
            left.add("N");
            
            ArrayList right = new ArrayList();
            boolean conflict = false;
            String inputString = list.get(i);
            for(int index=0; index < inputString.length(); index++){
                if(index % 2 != 0){
                    //Going to left
                    //Check if character does not exist
                    //Check if character is N
                    if(!right.contains(Character.toString(inputString.charAt(index))) 
                            && Character.toString(inputString.charAt(index)).compareTo("N") != 0){
                        conflict = true;
                        break;
                    }
                    right.remove("M");
                    left.add("M");
                    right.remove(Character.toString(inputString.charAt(index)));
                    left.add(Character.toString(inputString.charAt(index)));
                    
                } else {
                    if(!left.contains(Character.toString(inputString.charAt(index))) && 
                            Character.toString(inputString.charAt(index)).compareTo("N")!=0){
                        conflict = true;
                        break;
                    }
                    left.remove("M");
                    right.add("M");
                    left.remove(Character.toString(inputString.charAt(index)));
                    right.add(Character.toString(inputString.charAt(index)));
                }
                
                //Check if valid partnering for left state
                if (left.contains("L") && left.contains("R") && !left.contains("M")){
                    conflict = true;
                    break;
                } else if (left.contains("R") && left.contains("C") && !left.contains("M")){
                    conflict = true;
                    break;
                }
                
                //Check if valid partnering for right state
                if(right.contains("L") && right.contains("R") && !right.contains("M")){
                    conflict = true;
                    break;
                } else if (right.contains("R") && right.contains("C") && !right.contains("M")){
                    conflict = true;
                    break;
                }
            }
            
             left.removeAll(Collections.singleton("N"));
             
             if (!conflict && left.isEmpty()){
                 System.out.println("OK");
                 writer.println("OK");
             } else {
                 System.out.println("NG");
                 writer.println("NG");
             }
            
        }
    }
}
