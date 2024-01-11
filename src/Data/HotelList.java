package Data;


import Application.Utilities.Inputter;
import Application.Utilities.Menu;
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

    private Scanner sc = new Scanner(System.in);
    private DataValidation dv = new DataValidation();

    public HotelList() {
        super();
    }

    public void addNewHotel() {
        Hotel h = new Hotel();
        String id;
        boolean choice = true;
        while (choice) {
            id = dv.inputId(this);
            h.setId(id);
            dv.inputName(h);
            dv.inputRoomAvailable(h);
            dv.inputAddress(h);
            dv.inputPhoneNumber(h);
            dv.inputRating(h);
            add(h);
            System.out.println("\nThe new Hotel has been added successfully!");
            choice = Inputter.getYesOrNo("Do you want to continuously add new Hotel?(Y/N): ");
        }
    }

    public Hotel search(String id) {
        for (Hotel x : this) 
            if (id.equalsIgnoreCase(x.getId())) 
                return x;
        return null;
    }

    public void checkExist() {
        String id;
        boolean choice = true;
        while (choice) {
            id = Inputter.getPatternString("Enter Hotel_ID you want to check: ", "H\\d+");
            if (search(id) != null) {
                System.out.println("Hotel exists!");
                System.out.println(search(id));
                System.out.println();
            }
            else 
                System.out.println("No Hotel found!\n");
            choice = Inputter.getYesOrNo("Do you want to continuously check exist Hotel?(Y/N): ");
        }
    }
    
    public void update() {
        String id = Inputter.getPatternString("Enter Hotel_ID you want to update: ", "H\\d+");
        Hotel x = search(id);
        if (x == null) {
            System.out.println("\nThe Hotel does not exist!");
            return;
        }
        System.out.println("\nFound! The hotel is: \n" + x + "\n");
        dv.updateName(x);
        dv.updateRoomAvailable(x);
        dv.updateAddress(x);
        dv.updatePhoneNumber(x);
        dv.updateRating(x);
        System.out.println("\nUpdate successfully!");
        System.out.println("\nHere is the hotel after updating:\n" + x);
    }
    
    public void delete() {
        String id = Inputter.getPatternString("Enter Hotel_ID you want to delete: ", "H\\d+");
        Hotel x = search(id);
        if (x == null) {
            System.out.println("The Hotel does not exist!");
            return;
        }
        System.out.println("Found!");
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
        String[] opts = {"Search hotel by ID",
                         "Search hotel by Name"};
        int choice;
        do {
            choice = Menu.getChoice(opts, "SEARCH HOTEL");
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
        String id = Inputter.getPatternString("Enter Hotel_ID you want to search: ", "H\\d+");
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
        System.out.println("Here is the list:");
        for (Hotel x : list) 
            System.out.println(x);
        System.out.println("------------------");
        System.out.println("Total: " + list.size() + " hotel(s)");
    }
}
