/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;

public class FileString {
    public static String FileToString(File f) {
        try {
            String txt="";
            Scanner scan=new Scanner(f);
            while(scan.hasNextLine()){
                txt+=scan.nextLine();
                if(scan.hasNextLine())txt+="\n";
            }
            return txt;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileString.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public static void StringToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
           
        } catch (IOException ex) {
        }
    }
    
}
