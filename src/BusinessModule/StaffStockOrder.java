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
public class StaffStockOrder
{
    private String staffStockID;
    private int stationaryStockID;
    private int quantity;
    private double price;

    public StaffStockOrder(int stationaryStockID, int quantity, double price)
    {
        this.stationaryStockID = stationaryStockID;
        this.quantity = quantity;
        this.price = price;
    }

    
    public StaffStockOrder(String staffStockID, int stationaryStockID, int quantity, double price)
    {
        this.staffStockID = staffStockID;
        this.stationaryStockID = stationaryStockID;
        this.quantity = quantity;
        this.price = price;
    }

    public StaffStockOrder()
    {
    }

    public String getStaffStockID()
    {
        return staffStockID;
    }

    public void setStaffStockID(String staffStockID)
    {
        this.staffStockID = staffStockID;
    }

    public int getStationaryStockID()
    {
        return stationaryStockID;
    }

    public void setStationaryStockID(int stationaryStockID)
    {
        this.stationaryStockID = stationaryStockID;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public List<StaffStockOrder> GetOrderDetails(String staffStockID)
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<StaffStockOrder>) sc.GetOrderDetails(staffStockID);
    }
    
    public StaffOrder GetStaffOrderID(String staffStockID)
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (StaffOrder) sc.GetStaffOrderID(staffStockID);
    }
}
