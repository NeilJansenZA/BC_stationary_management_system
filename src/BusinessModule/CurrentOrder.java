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

    private String staffUsername;
    
    private int staffID;
    private double totalPrice;
    
    private boolean viewOrder;
    private boolean viewStaffOrder;
    private boolean viewStaffPending;

    public boolean isViewStaffPending()
    {
        return viewStaffPending;
    }

    public void setViewStaffPending(boolean viewStaffPending)
    {
        this.viewStaffPending = viewStaffPending;
    }

    public boolean isViewOrder()
    {
        return viewOrder;
    }

    public void setViewOrder(boolean viewOrder)
    {
        this.viewOrder = viewOrder;
    }

    public boolean isViewStaffOrder()
    {
        return viewStaffOrder;
    }

    public void setViewStaffOrder(boolean viewStaffOrder)
    {
        this.viewStaffOrder = viewStaffOrder;
    }

    public CurrentOrder(String orderID, String staffStockID, List<StaffStockOrder> orderStock, Date currentDate, double orderTotal, Date approvalDate, String staffUsername, double totalPrice)
    {
        this.orderID = orderID;
        this.staffStockID = staffStockID;
        this.orderStock = orderStock;
        this.currentDate = currentDate;
        this.orderTotal = orderTotal;
        this.approvalDate = approvalDate;
        this.staffUsername = staffUsername;
        this.totalPrice = totalPrice;
    }
    
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

    public CurrentOrder()
    {
    }
    
    

    public String getStaffUsername()
    {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername)
    {
        this.staffUsername = staffUsername;
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
    
    public void ApproveOrder(List<StationaryStock> stockList)
    {
        DataConnection dc = new DataConnection();
        Order order = new Order(SecurityModule.IDCreation.CreateOrderID(), new StaffStockOrder().GetStaffOrderID(this.staffStockID), new Date());
        order.getStaffOrder().setOrderStatus("Approved");
        dc.InsertOrder(order);
        
        for (StationaryStock stationaryStock : stockList)
        {
            dc.UpdateStockEntry(stationaryStock);
        }
    }
    
    public void DenyOrder()
    {
        DataConnection dc = new DataConnection();
        StaffOrder so = new StaffStockOrder().GetStaffOrderID(this.staffStockID);
        so.setOrderStatus("Request Denied");
        dc.DenyOrder(so);
    }

    @Override
    public String toString()
    {
        return "CurrentOrder{" + "orderID=" + orderID + ", staffStockID=" + staffStockID + ", orderStock=" + orderStock + ", currentDate=" + currentDate + ", orderTotal=" + orderTotal + ", approvalDate=" + approvalDate + ", staffUsername=" + staffUsername + ", staffID=" + staffID + ", totalPrice=" + totalPrice + ", viewOrder=" + viewOrder + ", viewStaffOrder=" + viewStaffOrder + ", viewStaffPending=" + viewStaffPending + '}';
    }
    
    
}
