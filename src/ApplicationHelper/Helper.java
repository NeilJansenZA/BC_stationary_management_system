/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationHelper;

import PresentationModule.BC_StationaryManagementSystem;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.FocusManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Helper
{
    private static Window GetCurrentActiveWindow()
    {
        return FocusManager.getCurrentManager().getActiveWindow();
    }
    public static void DisplayError(String errorMessage)
    {
        try
        {
            Window activeWindow = GetCurrentActiveWindow();
            if(activeWindow != null)
            {
                JOptionPane.showConfirmDialog(activeWindow, errorMessage, "Belgium Campus Stationary Management System", JOptionPane.DEFAULT_OPTION);
            }
            else
            {
                JOptionPane.showConfirmDialog(null, errorMessage, "Belgium Campus Stationary Management System", JOptionPane.DEFAULT_OPTION);
            }
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public static void DisplayError(String errorMessage, String title)
    {
        try
        {
            Window activeWindow = GetCurrentActiveWindow();
            if(activeWindow != null)
            {
                JOptionPane.showConfirmDialog(activeWindow, errorMessage, title, JOptionPane.DEFAULT_OPTION);
            }
            else
            {
                JOptionPane.showConfirmDialog(null, errorMessage, title, JOptionPane.DEFAULT_OPTION);
            }
        }
        catch(Exception ex)
        {
            
        }
    }
}
