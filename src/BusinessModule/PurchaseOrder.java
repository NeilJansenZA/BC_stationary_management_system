/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import java.util.Date;

/**
 *
 * @author Neil
 */
public class PurchaseOrder
{
    private int purchaseOrderID;
    private int stationaryStockID;
    private Date purchaseDate;
    private int quantity;
    private int price;

    public PurchaseOrder(int purchaseOrderID, int stationaryStockID, Date purchaseDate, int quantity, int price)
    {
        this.purchaseOrderID = purchaseOrderID;
        this.stationaryStockID = stationaryStockID;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.price = price;
    }

    public PurchaseOrder(int stationaryStockID, Date purchaseDate, int quantity, int price)
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

    public int getPurchaseOrderID()
    {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(int purchaseOrderID)
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

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
}
