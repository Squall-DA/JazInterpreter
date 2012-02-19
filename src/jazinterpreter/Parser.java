/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jazinterpreter;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Parser {

    public static ArrayList<Node> ToCommands(String fileName) {
        ArrayList<Node> commands = new ArrayList<Node>();
        int i = 0;
        String temp = null;
        Scanner input;
        try {
            input = new Scanner (new File(fileName));
        
            while (input.hasNext()){
                commands.add(new Node());
                temp = input.nextLine();
                temp.trim();
                if(temp.contains(" ")){
                    commands.get(i).setKeyword(temp.substring(0,temp.indexOf(" ")));
                    commands.get(i).setArgument(temp.substring(temp.indexOf(" ")+1));
                }
                else if(temp.equalsIgnoreCase("")){    i++; continue;}
                
                else{
                    commands.get(i).setKeyword(temp);
                }
                i++;
            }
        
        
            input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commands;
    }


    public static HashMap<String, Integer> GetLabelLocations(ArrayList<Node> input) {
        HashMap labelLoc = new HashMap();
        
        for(int i=0; i<input.size();i++){
            if(input.get(i).getKeyword().equalsIgnoreCase("label")){
                labelLoc.put(input.get(i).getArgument(), i);
            }
        }
        
        return labelLoc;
    }

}
