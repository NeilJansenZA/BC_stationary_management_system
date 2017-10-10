/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityModule;

import Authentication.*;
import BusinessModule.Staff;
import DataAccessModule.DataConnection;

/**
 *
 * @author Neil
 */
public class Internal
{
    public boolean ValidateUser(String username, String password)
    {
        int userID = -3;
        userID = ValidUser(username, password);

        if (userID == -3)
        {
            AuthenticationSettings.setValidationError("Invalid Login Information");
        } 
        if (userID == -4)
        {
            AuthenticationSettings.setValidationError("Your registration has been succesful, but an admin has yet to approve your entry");
        } 
        if (userID == -5)
        {
            AuthenticationSettings.setAdminConnected(true);
            return true;
        } 
        if (userID > -1)
        {
            AuthenticationSettings.setConnected(true);
            AuthenticationSettings.setCurrentUserID(userID);
            AuthenticationSettings.setConnectedStaff(StaffConnect());
            return true;
        } 
        else
        {
            AuthenticationSettings.setConnected(false);
            return false;
        }
    }

    private Staff StaffConnect()
    {
        String validationError = "";
        DataConnection dc = new DataConnection();
        try
        {
            Staff staff = new Staff().ReadStaffMember();
            if (staff == null)
            {
                return null;
            } else
            {
                return staff;
            }
        } catch (Exception e)
        {
            validationError = e.getMessage();
            return null;
        }
    }

    private int ValidUser(String username, String password)
    {
        int userID = -2;
        int result = 0;
        DataConnection dc = new DataConnection();

        try
        {
            result = dc.Login(username, password);
            if (result != 0)
            {
                userID = result;
            } 
            else
            {
                userID = 0;
            }
        } 
        catch (Exception e)
        {
            userID = -2;
            AuthenticationSettings.setValidationError(e.toString());
        }

        return userID;
    }

    public static void GetAuthenticationSettings()
    {
        String connectionString = "";
        String username = "";
        String password = "";

        connectionString = "jdbc:mysql://localhost:3306/bc_stationary_management_system";
        username = "root";
        password = "";

        AuthenticationSettings.setConnectionString(connectionString);
        AuthenticationSettings.setConnectionUsername(username);
        AuthenticationSettings.setConnectionPassword(password);
    }
}
