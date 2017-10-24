/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityModule;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Neil
 */
public class IDCreation
{
    public static String CreateStaffStockID()
    {
        String createdID = "";
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int al = alphabet.length();
        
        Date dt = new Date();
        Date date = new java.sql.Date(dt.getTime());
        String stringDate = date.toString().replaceAll("-", "");
        stringDate = stringDate.substring(2, 6);
        Random r = new Random();
        
        for (int i = 0; i < 4; i++)
        {
            createdID += alphabet.charAt(r.nextInt(al));
        }
        
        createdID += stringDate;
        
        return createdID;
    }
    
    public static String CreateOrderID()
    {
        String createdID = "";
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int al = alphabet.length();
        
        Date dt = new Date();
        Date date = new java.sql.Date(dt.getTime());
        String stringDate = date.toString().replaceAll("-", "");
        stringDate = stringDate.substring(2, 6);
        Random r = new Random();
        
        for (int i = 0; i < 4; i++)
        {
            createdID += alphabet.charAt(r.nextInt(al));
        }
        
        createdID += stringDate;
        
        return createdID;
    }
}
