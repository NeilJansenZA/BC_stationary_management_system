/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationModule;

import ApplicationHelper.Helper;
import Authentication.AuthenticationSettings;
import PresentationModule.JLayourForms.JAdminModule;
import PresentationModule.JLayourForms.JStaffModule;
import javax.swing.JOptionPane;


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
    
}
