/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import RemoteClient.ServerConnection;
import java.util.List;

/**
 *
 * @author Neil
 */
public class StationaryCategory
{
    private int stationaryCategoryID;
    private String name;
    
    public StationaryCategory() {}
    
    public StationaryCategory GetStationaryCategory(int stationaryCategoryID)
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (StationaryCategory) sc.GetStationaryCategory(stationaryCategoryID);
    }
    
    public List<StationaryCategory> ReadStationaryCategory()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<StationaryCategory>) sc.ReadStationaryCategory();
    }

    public StationaryCategory(int stationaryCategoryID, String name)
    {
        this.stationaryCategoryID = stationaryCategoryID;
        this.name = name;
    }

    public int getStationaryCategoryID()
    {
        return stationaryCategoryID;
    }

    public void setStationaryCategoryID(int stationaryCategoryID)
    {
        this.stationaryCategoryID = stationaryCategoryID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
   
}
