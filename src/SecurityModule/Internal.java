/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityModule;

import ApplicationHelper.Helper;
import Authentication.*;
import BusinessModule.Staff;
import RemoteClient.ServerConnection;

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
        if (userID > 0)
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
            AuthenticationSettings.setValidationError(e.toString());
            return null;
        }
    }

    private int ValidUser(String username, String password)
    {
        int userID = -2;
        int result = 0;
        ServerConnection sc = ServerConnection.GetInstance();

        try
        { 
            result = sc.Login(username, password);
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
}
