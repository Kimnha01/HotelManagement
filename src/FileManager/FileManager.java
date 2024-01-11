/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileManager;

import Data.Hotel;
import Data.HotelList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Kim Nha
 */
public class FileManager {
    
    public boolean loadFromFile(HotelList list, String fileName) {
        File f = new File(fileName);
        if (!f.exists()) 
            return false;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line = "";
            while ((line = bf.readLine()) != null) {
                String token[] = line.split(",\\s+");
                String id = token[0];
                String name = token[1];
                int roomAvailable = Integer.parseInt(token[2]);
                String address = "";
                for (int i = 3; i < token.length - 3; i++) 
                    address += token[i] + ", ";
                address += token[token.length - 3];
                String phoneNumber = token[token.length - 2];
                int rating = Integer.parseInt(token[token.length - 1]);
                list.add(new Hotel(id, name, roomAvailable, address, phoneNumber, rating));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean saveToFile(HotelList list, String fileName) {
        File f = new File(fileName);
        if (!f.exists()) 
            return false;
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Hotel x : list) 
                pw.println(x);
            pw.close();
            fw.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
