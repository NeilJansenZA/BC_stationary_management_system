/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationHelper;

import Authentication.Authentication;
import Authentication.AuthenticationSettings;

/**
 *
 * @author Neil
 */
public class Startup
{
    public static boolean Logon()
    {
        try
        {
            Authentication logon = new Authentication();
            
            boolean authenticated;
            
            authenticated = logon.Logon();
            
            if(authenticated)
            {
                return true;
            }
            else
            {
                if(AuthenticationSettings.getValidationError() != null)
                {
                    Helper.DisplayError("Application Will Now Terminate");
                }
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return false;
    }
}
