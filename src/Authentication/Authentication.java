/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import PresentationModule.JLayourForms.LoginDialog;
import SecurityModule.Internal;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Authentication
{
    public Authentication()
    {
        
    }
    
    public boolean Logon()
    {
        Internal.GetAuthenticationSettings();
        AuthenticationSettings.setConnected(true);
        
        int register = 1;
        boolean dialogResult = false;
        
        LoginDialog ld = new LoginDialog(null, true);
        ld.setVisible(true);
        while (register != 0)
        {        
            dialogResult = ld.getDialog();
            register = ld.isRegistering();
        }
        
        if(dialogResult)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
