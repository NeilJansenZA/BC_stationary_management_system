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
public class StationaryCategory
{
    private DataConnection dc = new DataConnection();
    
    private int stationaryCategoryID;
    private String name;
    
    public StationaryCategory() {}
    
    public StationaryCategory GetStationaryCategory(int stationaryCategoryID)
    {
        return (StationaryCategory) dc.GetStationaryCategory(stationaryCategoryID);
    }
    
    public List<StationaryCategory> ReadStationaryCategory()
    {
        return (List<StationaryCategory>) dc.ReadStationaryCategory();
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
