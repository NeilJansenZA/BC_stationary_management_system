/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import RemoteClient.ServerConnection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class PurchaseOrder
{
    private String purchaseOrderID;
    private int stationaryStockID;
    private Date purchaseDate;
    private int quantity;
    private double price;

    public PurchaseOrder(String purchaseOrderID, int stationaryStockID, Date purchaseDate)
    {
        this.purchaseOrderID = purchaseOrderID;
        this.stationaryStockID = stationaryStockID;
        this.purchaseDate = purchaseDate;
    }
    

    public PurchaseOrder(String purchaseOrderID, int stationaryStockID, Date purchaseDate, int quantity, double price)
    {
        this.purchaseOrderID = purchaseOrderID;
        this.stationaryStockID = stationaryStockID;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.price = price;
    }

    public PurchaseOrder(int stationaryStockID, Date purchaseDate, int quantity, double price)
    {
        this.stationaryStockID = stationaryStockID;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.price = price;
    }  

    public PurchaseOrder(int stationaryStockID)
    {
        this.stationaryStockID = stationaryStockID;
    }

    public String getPurchaseOrderID()
    {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(String purchaseOrderID)
    {
        this.purchaseOrderID = purchaseOrderID;
    }

    public int getStationaryStockID()
    {
        return stationaryStockID;
    }

    public void setStationaryStockID(int stationaryStockID)
    {
        this.stationaryStockID = stationaryStockID;
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
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
    
    public void InsertPurchaseOrder()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        sc.InsertPurchaseOrder(this);
    }

    public PurchaseOrder() {
    }
    
    public void UpdateAndRemovePurchaseOrder()
    {
        int currentStock = 0;
        currentStock = new StationaryStock().GetAllProductFromID(this.getStationaryStockID()).getQuantity();
        currentStock += this.getQuantity();
        ServerConnection sc = ServerConnection.GetInstance();
        sc.UpdateAndRemovePurchaseOrder(this, currentStock);
    }
    
    public List<PurchaseOrder> LoadAllPurchaseOrders()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<PurchaseOrder>) sc.LoadPurchaseOrders();
    }
}
