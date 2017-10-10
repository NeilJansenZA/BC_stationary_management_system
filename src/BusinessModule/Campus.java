/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import DataAccessModule.DataConnection;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Campus
{
    private DataConnection dc = new DataConnection();
    
    private int campusID;
    private String campusName;
    private String campusAddress;
    private String campusProvince;
    private String campusCity;
    
    public Campus()
    {
        
    }
    
    public Campus GetCampus(int campusID)
    {
        return (Campus) dc.GetCampus(campusID);
    }
    
    public List<Campus> ReadCampus()
    {
        return (List<Campus>) dc.ReadCampus();
    }

    public Campus(int campusID, String campusName, String campusAddress, String campusProvince, String campusCity)
    {
        this.campusID = campusID;
        this.campusName = campusName;
        this.campusAddress = campusAddress;
        this.campusProvince = campusProvince;
        this.campusCity = campusCity;
    }

    public int getCampusID()
    {
        return campusID;
    }

    public void setCampusID(int campusID)
    {
        this.campusID = campusID;
    }

    public String getCampusName()
    {
        return campusName;
    }

    public void setCampusName(String campusName)
    {
        this.campusName = campusName;
    }

    public String getCampusAddress()
    {
        return campusAddress;
    }

    public void setCampusAddress(String campusAddress)
    {
        this.campusAddress = campusAddress;
    }

    public String getCampusProvince()
    {
        return campusProvince;
    }

    public void setCampusProvince(String campusProvince)
    {
        this.campusProvince = campusProvince;
    }

    public String getCampusCity()
    {
        return campusCity;
    }

    public void setCampusCity(String campusCity)
    {
        this.campusCity = campusCity;
    }
}
