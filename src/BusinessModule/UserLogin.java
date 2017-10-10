/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import DataAccessModule.DataConnection;
import static SecurityModule.EncryptionSource.GenerateHash;
import SecurityModule.Internal;

/**
 *
 * @author Neil
 */
public class UserLogin implements ISecurity
{
    private String username;
    private String password;

    public UserLogin(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    @Override
    public void HashSecurity()
    {
        this.password = GenerateHash(this.password);
    }

    @Override
    public boolean Login()
    {
        HashSecurity();
        Internal inte = new Internal();
        return inte.ValidateUser(this.username, this.password);
    }
    
}
