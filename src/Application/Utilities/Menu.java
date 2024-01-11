/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Utilities;

/**
 *
 * @author Kim Nha
 */
public class Menu {
    public static int getChoice(String[] opts, String menuTitle) {
        System.out.println("================= *" + menuTitle + "* ===============");
        for (int i = 0; i < opts.length; i++)
            System.out.println("  " + (i + 1) + "- " + opts[i]);
        System.out.println("  Others quit!");
        System.out.println("=============================================");
        return Inputter.getNonNegativeInteger("Enter your choice: ");
    }
}
