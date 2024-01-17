/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyUtil;

import Data.Hotel;
import Data.HotelList;
import MyUtil.Inputter;

/**
 *
 * @author Kim Nha
 */
public class DataValidation {

    public static String inputId(HotelList list) {
        String id;
        do {
            id = Inputter.getPatternString("Enter Hotel_ID: ", "H\\d{2}", "The format of ID is Hxx");
            if (list.search(id) != null)
                System.out.println("Duplicated ID! Try with another!");
            else
                return id;
        } while (true);
    }
    
    public static void inputName(Hotel h) {
        String name = Inputter.getNonBlankString("Enter Hotel_Name: ");
        h.setName(name);
    }
    
    public static void inputRoomAvailable(Hotel h) {
        int roomAvailable = Inputter.getNonNegativeInteger("Enter Room_Available: ");
        h.setRoomAvailable(roomAvailable);
    }
    
    public static void inputAddress(Hotel h) {
        String address = Inputter.getNonBlankString("Enter Hotel_Address: ");
        h.setAddress(address);
    }

    public static void inputPhoneNumber(Hotel h) {
        String phoneNumber = Inputter.getPatternString("Enter Hotel_Phone (0xxxxxxxxx): ", 
                                                    "0\\d{9}", "The format of Phone Number is 0xxxxxxxxx");
        h.setPhoneNumber(phoneNumber);
    }

    public static void inputRating(Hotel h) {
        int rating = Inputter.getIntegerInRange("Enter Hotel_Rating (0-6): ", 0, 6);
        h.setRating(rating);
    }
    
    public static void updateName(Hotel h) {
        String s = Inputter.getString("Enter Name_Update (Leave blank if not updating): ");
        if (!s.isEmpty())
            h.setName(s);
    }
    
    public static void updateRoomAvailable(Hotel h) {
        String s = "a";
        int roomAvailable = -1;
        while (!s.isEmpty() && roomAvailable < 0) {
            s = Inputter.getString("Enter Room_Update (Leave blank if not updating): ");
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
    
    public static void updateAddress(Hotel h) {
        String s = Inputter.getString("Enter Address_Update (Leave blank if not updating): ");
        if (!s.isEmpty())
            h.setAddress(s);
    }
    
    public static void updatePhoneNumber(Hotel h) {
        String phoneNumber;
        do {
            phoneNumber = Inputter.getString("Enter Phone_Update (0xxxxxxxxx) (Leave blank if not updating): ");
            if (phoneNumber.isEmpty())
                return;
            if (!phoneNumber.matches("0\\d{9}"))
                System.out.println("The format of Phone Number is 0xxxxxxxxx");
        } while (!phoneNumber.isEmpty() && !phoneNumber.matches("0\\d{9}"));
        h.setPhoneNumber(phoneNumber);
    }
    
    public static void updateRating(Hotel h) {
        String s = "a";
        int rating = -1;
        while (!s.isEmpty() && (rating < 0 || rating > 6)) {
            s = Inputter.getString("Enter Rating_Update (Leave blank if not updating): ");
            try {
                rating = Integer.parseInt(s);
                if (rating < 0 || rating > 5) {
                    System.out.println("The input must be from 0 to 6!");
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
