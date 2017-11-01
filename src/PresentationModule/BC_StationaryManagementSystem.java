/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationModule;

import ApplicationHelper.Helper;
import Authentication.AuthenticationSettings;
import PresentationModule.JLayoutForms.JAdminModule;
import PresentationModule.JLayoutForms.JStaffModule;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Neil
 */
public class BC_StationaryManagementSystem implements Runnable{

    public static void main(String[] args) {
        new Thread(new BC_StationaryManagementSystem()).start();
    }

    @Override
    public void run()
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            if(ApplicationHelper.Startup.Logon())
            {
                if(AuthenticationSettings.isAdminConnected())
                {
                    JOptionPane.showConfirmDialog(null, "Welcome Administrator", "Authentication Succesful", JOptionPane.DEFAULT_OPTION);
                    
                    JAdminModule jam = new JAdminModule();
                    jam.setVisible(true);               
                }
                else if(AuthenticationSettings.isConnected())
                {
                    JOptionPane.showConfirmDialog(null, "Welcome " + AuthenticationSettings.getConnectedStaff().getStaffUsername(), "Authentication Succesful", JOptionPane.DEFAULT_OPTION);
                    JStaffModule jsm = new JStaffModule();
                    jsm.setVisible(true);
                }
            }
            else
            {
                System.exit(0);
            }
        }
        catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException ex) 
        {
            Helper.DisplayError(ex.toString(), "Error in Setting UI Theme");
        }      
    }
    
}
