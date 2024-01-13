package Application.UI;


import Data.HotelList;
import Application.Utilities.Menu;
import FileManager.FileManager;

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
                    list.printDescendingByName();
                    break;
                default:
                    System.out.println("Quit! See you next time!");
            }
            fm.saveToFile(list, "Hotels.txt");
            System.out.println();
        } while (choice >=1 && choice <= 6);
    }
}
