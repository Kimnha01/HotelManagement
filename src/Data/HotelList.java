package Data;


import MyUtil.DataValidation;
import MyUtil.Inputter;
import UI.Menu;
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kim Nha
 */
public class HotelList extends ArrayList<Hotel> {

    public HotelList() {
        super();
    }
    
    public boolean loadFromFile(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) 
            return false;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line = "";
            while ((line = bf.readLine()) != null) {
                String token[] = line.split("-");
                if (token.length == 6) {
                    String id = token[0];
                    String name = token[1];
                    int roomAvailable = Integer.parseInt(token[2]);
                    String address = token[3];
                    String phoneNumber = token[4];
                    int rating = Integer.parseInt(token[5]);
                    add(new Hotel(id, name, roomAvailable, address, phoneNumber, rating));
                }
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean saveToFile(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) 
            return false;
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Hotel x : this) 
                pw.println(x);
            pw.close();
            fw.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void addNewHotel() {
        String id;
        boolean choice = true;
        while (choice) {
            Hotel h = new Hotel();
            id = DataValidation.inputId(this);
            h.setId(id);
            DataValidation.inputName(h);
            DataValidation.inputRoomAvailable(h);
            DataValidation.inputAddress(h);
            DataValidation.inputPhoneNumber(h);
            DataValidation.inputRating(h);
            add(h);
            System.out.println("\nThe new Hotel has been added successfully!\n");
            choice = Inputter.getYesOrNo("Do you want to continuously add new Hotel?(Y/N): ");
            System.out.println();
        }
    }

    public Hotel search(String id) {
        for (Hotel x : this) 
            if (id.equalsIgnoreCase(x.getId())) 
                return x;
        return null;
    }

    public void checkExist() {
        if (isEmpty()) {
            System.out.println("The Hotel List is empty! Nothing to check!");
            return;
        }
        String id;
        boolean choice = true;
        while (choice) {
            id = Inputter.getPatternString("Enter Hotel_ID you want to check: ", "H\\d{2}", "The format of ID is Hxx");
            if (search(id) != null) {
                System.out.println("\nHotel exists!");
                System.out.println(getHeading());
                search(id).showProfile();
            }
            else 
                System.out.println("\nNo Hotel found!");
            System.out.println();
            choice = Inputter.getYesOrNo("Do you want to continuously check exist Hotel?(Y/N): ");
            System.out.println();
        }
    }
    
    public void update() {
        if (isEmpty()) {
            System.out.println("The Hotel List is empty! Nothing to update!");
            return;
        }
        String id = Inputter.getPatternString("Enter Hotel_ID you want to update: ", "H\\d{2}", "The format of ID is Hxx");
        Hotel x = search(id);
        if (x == null) {
            System.out.println("\nThe Hotel does not exist!");
            return;
        }
        System.out.println("\nFound! The Hotel before updating is:");
        System.out.println(getHeading());
        x.showProfile();
        System.out.println();
        DataValidation.updateName(x);
        DataValidation.updateRoomAvailable(x);
        DataValidation.updateAddress(x);
        DataValidation.updatePhoneNumber(x);
        DataValidation.updateRating(x);
        System.out.println("\nUpdate successfully!");
        System.out.println("\nHere is the Hotel after updating:");
        System.out.println(getHeading());
        x.showProfile();
    }
    
    public void delete() {
        if (isEmpty()) {
            System.out.println("The Hotel List is empty! Nothing to delete!");
            return;
        }
        String id = Inputter.getPatternString("Enter Hotel_ID you want to delete: ", "H\\d{2}", "The format of ID is Hxx");
        Hotel x = search(id);
        if (x == null) {
            System.out.println("\nThe Hotel does not exist!");
            return;
        }
        System.out.println("\nFound! The Hotel is:");
        System.out.println(getHeading());
        x.showProfile();
        System.out.println();
        boolean choice = Inputter.getYesOrNo("Do you ready to delete this hotel? (Y/N): ");
        System.out.println();
        if (choice == false)
            System.out.println("Delete fail!");
        else {
            remove(x);
            System.out.println("Delete successfully!");
        }
    }
    
    public void searchHotel() {
        if (isEmpty()) {
            System.out.println("The Hotel List is empty! Nothing to search!");
            return;
        }
        String[] opts = {"Search hotel by ID",
                        "Search hotel by Name"};
        int choice;
        do {
            choice = Menu.getChoice(opts, "SEARCH");
            System.out.println();
            switch(choice) {
                case 1:
                    searchByID();
                    break;
                case 2: 
                    searchByName();
                    break;
                default:
                    System.out.println("Quit to Main Menu!");
            }
            System.out.println();
        } while (choice == 1 || choice == 2);
    }
    
    public ArrayList<Hotel> searchById(String id) {
        ArrayList<Hotel> listById = new ArrayList();
        for (Hotel x: this)
            if (x.getId().contains(id))
                listById.add(x);
        return listById;
    }
    
    public void searchByID() {        
        String id = Inputter.getNonBlankString("Enter Hotel_ID you want to search: ").toUpperCase();
        System.out.println();
        ArrayList<Hotel> listById = searchById(id);
        if (listById.isEmpty())
            System.out.println("No Hotel found!");
        else {
            Collections.sort(listById, (Hotel x, Hotel y) -> {
                return y.getId().compareTo(x.getId());
            });
            printList(listById);
        }
    }
    
    public ArrayList<Hotel> searchByName(String name) {
        ArrayList<Hotel> listByName = new ArrayList();
        for (Hotel x: this)
            if (x.getName().equalsIgnoreCase(name))
                listByName.add(x);
        return listByName;
    }
    
    public void searchByName() {
        String name = Inputter.getNonBlankString("Enter Hotel_Name you want to search: ");
        System.out.println();
        ArrayList<Hotel> listByName = searchByName(name);
        if (listByName.isEmpty())
            System.out.println("No Hotel found!");
        else 
            printList(listByName);
    }
    
    public void printList(ArrayList<Hotel> list) {
        String heading = getHeading();
        System.out.println(heading);
        for (int i = 0; i < heading.length(); i++)
            System.out.print("-");
        System.out.println();
        for (Hotel x : list) 
            x.showProfile();
        System.out.println("------------------");
        System.out.println("Total: " + list.size() + " hotel(s)");
    }
    
    public void printDescendingByName() {
        if (isEmpty()) {
            System.out.println("The Hotel List is empty! Nothing to print!");
            return;
        }
        System.out.println("After sorting descending by name, the Hotel list is:\n");
        ArrayList<Hotel> list = new ArrayList();
        list.addAll(this);
        Collections.sort(list);
        printList(list);
    }
    
    public String getHeading() {
        return String.format("| %-5s| %-21s| %-5s | %-73s| %-10s | %-6s |", 
                                    "ID", "Name", "Rooms", "Address", "Phone", "Rating");
    }
}
