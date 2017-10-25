/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import DataAccessModule.DataConnection;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class StaffOrder
{
    private DataConnection dc = new DataConnection();
    private int staffOrderID;
    private int staffID;
    private String staffStockOrderID;
    private Date orderDate;
    private double orderTotal;
    private String orderStatus;

    public StaffOrder(int staffOrderID, int staffID, String staffStockOrderID, Date orderDate, double orderTotal, String orderStatus)
    {
        this.dc = new DataConnection();
        this.staffOrderID = staffOrderID;
        this.staffID = staffID;
        this.staffStockOrderID = staffStockOrderID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public StaffOrder(String staffStockOrderID, Date orderDate, double orderTotal, String orderStatus)
    {
        this.dc = new DataConnection();
        this.staffStockOrderID = staffStockOrderID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public StaffOrder(String staffStockOrderID, Date orderDate, double orderTotal)
    {
        this.dc = new DataConnection();
        this.staffStockOrderID = staffStockOrderID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }
    
    public StaffOrder(int staffID, String staffStockOrderID, Date orderDate, double orderTotal)
    {
        this.dc = new DataConnection();
        this.staffID = staffID;
        this.staffStockOrderID = staffStockOrderID;       
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public StaffOrder()
    {
    }

    public int getStaffOrderID()
    {
        return staffOrderID;
    }

    public void setStaffOrderID(int staffOrderID)
    {
        this.staffOrderID = staffOrderID;
    }
    

    public String getStaffStockOrderID()
    {
        return staffStockOrderID;
    }

    public void setStaffStockOrderID(String staffStockOrderID)
    {
        this.staffStockOrderID = staffStockOrderID;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }

    public int getStaffID()
    {
        return staffID;
    }

    public void setStaffID(int staffID)
    {
        this.staffID = staffID;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }
    
    public List<StaffOrder> LoadPendingOrders()
    {
        return dc.LoadMyPendingOrders();
    }
}
