/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationModule.JLayoutForms;

import ApplicationHelper.Helper;
import Authentication.AuthenticationSettings;
import BusinessModule.Staff;
import java.awt.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Validation
{

    public Validation()
    {
    }
    
    public boolean ValidateStockEntry(String productName, String category, String model, String price, String quantity)
    {
        try
        {
            if(productName.trim().isEmpty() || category.trim().isEmpty() ||  model.trim().isEmpty() ||  price.trim().isEmpty())
            {
                throw new ValidationException("One or more fields are still empty!");
            }
            else if(Integer.parseInt(quantity) == 0)
            {
                throw new ValidationException("Cannot Insert No Stock!");
            }
            else if(Double.parseDouble(price) == 0)
            {
                throw new ValidationException("Price Cannot be Zero");
            }
        }
        catch (ValidationException ve)
        {
            Helper.DisplayError(ve.getMessage());
            return false;
        }
        catch (NumberFormatException nfe)
        {
            Helper.DisplayError("Please Enter A decimal value for Price");
            return false;
        }

        return true;
    }
    
    public boolean ValidateStaffAccountInput(String name, String surname, String campus, String department, String cellNo, String email, String username, String password, String confirmPassword)
    {
        try
        {
            if (name.trim().isEmpty() || surname.trim().isEmpty() || campus.isEmpty() || department.isEmpty() || cellNo.trim().isEmpty() || email.trim().isEmpty() || username.trim().isEmpty())
            {
                throw new ValidationException("One or more fields are still empty!");
            } else if (cellNo.length() != 10)
            {
                throw new ValidationException("CellNo should be 10 numeric characters exactly!");
            } else if (!username.equals(AuthenticationSettings.getConnectedStaff().getStaffUsername()))
            {
                if(new Staff().CheckUsername(username))
                {
                    throw new ValidationException("Username already exists or is unavailable!");
                }
            } else if ((password.length() <= 7 || confirmPassword.length() <= 7) && (password.length() > 0))
            {
                throw new ValidationException("Password needs to be atleast 7 characters!");
            } else if (!password.equals(confirmPassword))
            {
                throw new ValidationException("Passwords do not match!");
            }
        } catch (ValidationException ve)
        {
            Helper.DisplayError(ve.getMessage(), name);
            return false;
        }

        return true;
    }
    
    public boolean ValidateStaffRegisterInput(String name, String surname, String campus, String department, String cellNo, String email, String username, String password, String confirmPassword)
    {
        try
        {
            if (name.trim().isEmpty() || surname.trim().isEmpty() || campus.isEmpty() || department.isEmpty() || cellNo.trim().isEmpty() || email.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty())
            {
                throw new ValidationException("One or more fields are still empty!");
            } else if (cellNo.length() != 10)
            {
                throw new ValidationException("CellNo should be 10 numeric characters exactly!");
            } else if (new Staff().CheckUsername(username))
            {
                throw new ValidationException("Username already exists or is unavailable!");
            } else if (password.length() <= 7 || confirmPassword.length() <= 7)
            {
                throw new ValidationException("Password needs to be atleast 7 characters!");
            } else if (!password.equals(confirmPassword))
            {
                throw new ValidationException("Passwords do not match!");
            }
        } catch (ValidationException ve)
        {
            Helper.DisplayError(ve.getMessage(), name);
            return false;
        }

        return true;
    }
    
    public boolean ValidateLoginInput(String username, String password)
    {
        try
        {
            if (username.trim().isEmpty() || password.trim().isEmpty())
            {
                throw new ValidationException("One or more fields cannot be empty!");
            }
        } catch (ValidationException ve)
        {
            JOptionPane.showConfirmDialog(null, "One or more fields cannot be empty!", "Input Validation Error!", JOptionPane.DEFAULT_OPTION);
            return false;
        }

        return true;
    }
}
