/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import DataAccessModule.DataConnection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class CurrentOrder
{
    private String orderID;
    
    private String staffStockID;
    private List<StaffStockOrder> orderStock;
    private Date currentDate;
    private double orderTotal;
    private Date approvalDate;

    
    private int staffID;
    private double totalPrice;

    public CurrentOrder(String orderID, String staffStockID, List<StaffStockOrder> orderStock, Date currentDate, int staffID, double totalPrice)
    {
        this.orderID = orderID;
        this.staffStockID = staffStockID;
        this.orderStock = orderStock;
        this.currentDate = currentDate;
        this.staffID = staffID;
        this.totalPrice = totalPrice;
    }

    public CurrentOrder(String orderID, String staffStockID, List<StaffStockOrder> orderStock, Date currentDate, Date approvalDate, int staffID, double totalPrice)
    {
        this.orderID = orderID;
        this.staffStockID = staffStockID;
        this.orderStock = orderStock;
        this.currentDate = currentDate;
        this.approvalDate = approvalDate;
        this.staffID = staffID;
        this.totalPrice = totalPrice;
    }
    
    public CurrentOrder(String staffStockID, List<StaffStockOrder> orderStock, Date currentDate, int staffID, double totalPrice)
    {
        this.staffStockID = staffStockID;
        this.orderStock = orderStock;
        this.currentDate = currentDate;
        this.staffID = staffID;
        this.totalPrice = totalPrice;
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }
    
    
    
    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public Date getApprovalDate()
    {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate)
    {
        this.approvalDate = approvalDate;
    }
    
    public String getStaffStockID()
    {
        return staffStockID;
    }

    public void setStaffStockID(String staffStockID)
    {
        this.staffStockID = staffStockID;
    }

    public List<StaffStockOrder> getOrderStock()
    {
        return orderStock;
    }

    public void setOrderStock(List<StaffStockOrder> orderStock)
    {
        this.orderStock = orderStock;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate)
    {
        this.currentDate = currentDate;
    }

    public int getStaffID()
    {
        return staffID;
    }

    public void setStaffID(int staffID)
    {
        this.staffID = staffID;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
    
    public void RequestCurrentOrder()
    {
        DataConnection dc = new DataConnection();
        dc.RequestCurrentOrder();
    }
    
    public void RequestCurrentOrderWithPurchaseOrder(List<PurchaseOrder> po)
    {
        DataConnection dc = new DataConnection();
        dc.RequestCurrentOrderWithPurchaseOrder(po);
    }
}
