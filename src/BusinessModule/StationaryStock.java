/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import RemoteClient.ServerConnection;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class StationaryStock implements Serializable
{
    private int stationaryStockID;
    private String productName;
    private int stationaryCategoryID;
    private String model;
    private double price;
    private int quantity;
    private Date dateOfEntryUpdate;
    
    public StationaryStock() {}

    public StationaryStock(int stationaryStockID, String productName, int stationaryCategoryID, String model, double price, int quantity)
    {
        this.stationaryStockID = stationaryStockID;
        this.productName = productName;
        this.stationaryCategoryID = stationaryCategoryID;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }

    public StationaryStock(int stationaryStockID, String productName, int stationaryCategoryID, String model, double price, int quantity, Date dateOfEntryUpdate)
    {
        this.stationaryStockID = stationaryStockID;
        this.productName = productName;
        this.stationaryCategoryID = stationaryCategoryID;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
        this.dateOfEntryUpdate = dateOfEntryUpdate;
    }

    public int getStationaryStockID()
    {
        return stationaryStockID;
    }

    public void setStationaryStockID(int stationaryStockID)
    {
        this.stationaryStockID = stationaryStockID;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public int getStationaryCategoryID()
    {
        return stationaryCategoryID;
    }

    public void setStationaryCategoryID(int stationaryCategoryID)
    {
        this.stationaryCategoryID = stationaryCategoryID;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Date getDateOfEntryUpdate()
    {
        return dateOfEntryUpdate;
    }

    public void setDateOfEntryUpdate(Date dateOfEntryUpdate)
    {
        this.dateOfEntryUpdate = dateOfEntryUpdate;
    }
    
    public void InsertStationaryStock()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        sc.InsertStationaryStock(this);
    }
    
    public List<StationaryStock> LoadStationaryStock()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<StationaryStock>) sc.LoadStationaryStock();
    }

    public StationaryStock(String productName, int stationaryCategoryID, String model, double price, int quantity)
    {
        this.productName = productName;
        this.stationaryCategoryID = stationaryCategoryID;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "StationaryStock{stationaryStockID=" + stationaryStockID + ", productName=" + productName + ", stationaryCategoryID=" + stationaryCategoryID + ", model=" + model + ", price=" + price + ", quantity=" + quantity + ", dateOfEntryUpdate=" + dateOfEntryUpdate + '}';
    }
    
    public void DeleteStockItem()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        sc.DeleteStockItem(this.stationaryStockID);
    }
    
    public void UpdateStockEntry()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        sc.UpdateStockEntry(this);
    }
    
    public String GetProductFromID(int productID)
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return sc.GetProductFromID(productID);
    }
    
    public StationaryStock GetAllProductFromID(int productID)
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (StationaryStock) sc.GetAllProductFromID(productID);
    }
}
