/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import BusinessModule.Staff;

/**
 *
 * @author Neil
 */
public class AuthenticationSettings
{
    private static int CurrentUserID;
    private static boolean Connected;
    private static String ValidationError;
    private static boolean AdminConnected;
    private static Staff ConnectedStaff;

    public static int getCurrentUserID()
    {
        return CurrentUserID;
    }

    public static void setCurrentUserID(int aCurrentUserID)
    {
        CurrentUserID = aCurrentUserID;
    }

    public static boolean isConnected()
    {
        return Connected;
    }

    public static void setConnected(boolean aConnected)
    {
        Connected = aConnected;
    }

    public static String getValidationError()
    {
        return ValidationError;
    }

    public static void setValidationError(String aValidationError)
    {
        ValidationError = aValidationError;
    }

    public static boolean isAdminConnected()
    {
        return AdminConnected;
    }

    public static void setAdminConnected(boolean aAdminConnected)
    {
        AdminConnected = aAdminConnected;
    }

    public static Staff getConnectedStaff()
    {
        return ConnectedStaff;
    }

    public static void setConnectedStaff(Staff aConnectedStaff)
    {
        ConnectedStaff = aConnectedStaff;
    }
}
