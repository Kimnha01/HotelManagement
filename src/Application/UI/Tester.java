package Application.UI;


import Data.HotelList;
import Data.Hotel;
import Application.Utilities.Inputter;
import Application.Utilities.Menu;
import FileManager.FileManager;
import java.util.Collections;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim Nha
 */
public class Tester {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        HotelList list = new HotelList();
        FileManager fm = new FileManager();
        fm.loadFromFile(list, "Hotels.txt");
        String[] opts = {"Add new Hotel",
                         "Check exists Hotel",
                         "Update Hotel information",
                         "Delete Hotel",
                         "Search Hotel",
                         "Display Hotel list (descending by name)"};
        int choice;
        do {           
            choice = Menu.getChoice(opts, "MAIN MENU");
            System.out.println();
            switch(choice) {
                case 1:
                    list.addNewHotel();
                    break;
                case 2:
                    list.checkExist();
                    break;
                case 3:
                    list.update();
                    break;
                case 4:
                    list.delete();
                    break;
                case 5:
                    list.searchHotel();
                    break;
                case 6:
                    Collections.sort(list, (Hotel x, Hotel y) -> {
                        return y.getName().compareToIgnoreCase(x.getName());
                    });
                    list.printList(list);
                    break;
                default:
                    System.out.println("Quit! See you next time!");
            }
            fm.saveToFile(list, "Hotels.txt");
            System.out.println();
        } while (choice >=1 && choice <= 6);
    }
   
    public static void searchByIdOrName(HotelList list) {
        int choice;
        do {
            System.out.println("-------------------------------|");
            System.out.println("|  1- Search by ID             |");
            System.out.println("|  2- Search by Hotel_Name     |");
            System.out.println("|  Others return to Main Menu  |");
            System.out.println("|-------------------------------");
            choice = Inputter.getNonNegativeInteger("Your choice is: ");
            switch(choice) {
                case 1:
                    list.searchByID();
                    break;
                case 2:
                    list.searchByName();
                    break;
            }
            System.out.println();
        } while (choice >=1 && choice <= 2);
    }
}
