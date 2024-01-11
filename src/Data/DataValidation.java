/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.Utilities.Inputter;
import java.util.Scanner;

/**
 *
 * @author Kim Nha
 */
public class DataValidation {
    
    private Scanner sc = new Scanner(System.in);

    public String inputId(HotelList list) {
        String id;
        do {
            id = Inputter.getPatternString("Enter Hotel_ID: ", "H\\d+");
            if (list.search(id) != null)
                System.out.println("Duplicated ID! Try with another!");
            else
                return id;
        } while (true);
    }
    
    public void inputName(Hotel h) {
        String name = Inputter.getNonBlankString("Enter Hotel_Name: ");
        h.setName(name);
    }
    
    public void inputRoomAvailable(Hotel h) {
        int roomAvailable = Inputter.getNonNegativeInteger("Enter Room_Available: ");
        h.setRoomAvailable(roomAvailable);
    }
    
    public void inputAddress(Hotel h) {
        String address = Inputter.getNonBlankString("Enter Hotel_Address: ");
        h.setAddress(address);
    }

    public void inputPhoneNumber(Hotel h) {
        String phoneNumber = Inputter.getNonBlankString("Enter Hotel_Phone: ");
        h.setPhoneNumber(phoneNumber);
    }

    public void inputRating(Hotel h) {
        int rating = Inputter.getIntegerInRange("Enter Hotel_Rating (0-5): ", 0, 5);
        h.setRating(rating);
    }
    
    public void updateName(Hotel h) {
        String s = Inputter.getString("Enter Name_Update: ");
        if (!s.isEmpty())
            h.setName(s);
    }
    
    public void updateRoomAvailable(Hotel h) {
        String s = "a";
        int roomAvailable = -1;
        while (!s.isEmpty() && roomAvailable < 0) {
            s = Inputter.getString("Enter Room_Update: ");
            try {
                roomAvailable = Integer.parseInt(s);
                if (roomAvailable < 0) {
                    System.out.println("The input must be a non-negative integer!");
                }
            } catch (Exception e) {
                if (s.isEmpty())
                    return;
                System.out.println("Invalid input!");
            }
        }
        h.setRoomAvailable(roomAvailable);
    }
    
    public void updateAddress(Hotel h) {
        String s = Inputter.getString("Enter Address_Update: ");
        if (!s.isEmpty())
            h.setAddress(s);
    }
    
    public void updatePhoneNumber(Hotel h) {
        String s = Inputter.getString("Enter Phone_Update: ");
        if (!s.isEmpty())
            h.setPhoneNumber(s);
    }
    
    public void updateRating(Hotel h) {
        String s = "a";
        int rating = -1;
        while (!s.isEmpty() && (rating < 0 || rating > 5)) {
            s = Inputter.getString("Enter Rating_Update: ");
            try {
                rating = Integer.parseInt(s);
                if (rating < 0 || rating > 5) {
                    System.out.println("The input must be from 0 to 5!");
                }
            } catch (Exception e) {
                if (s.isEmpty())
                    return;
                System.out.println("Invalid input!");
            }
        }
        h.setRating(rating);
    }
}
