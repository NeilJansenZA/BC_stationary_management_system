/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import Authentication.AuthenticationSettings;
import RemoteClient.ServerConnection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Order
{
    private String orderID;
    private StaffOrder staffOrder;
    private Date approvalDate;

    public Order()
    {
    }

    public Order(String orderID, StaffOrder staffOrder, Date approvalDate)
    {
        this.orderID = orderID;
        this.staffOrder = staffOrder;
        this.approvalDate = approvalDate;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public StaffOrder getStaffOrder()
    {
        return staffOrder;
    }

    public void setStaffOrder(StaffOrder staffOrder)
    {
        this.staffOrder = staffOrder;
    }

    public Date getApprovalDate()
    {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate)
    {
        this.approvalDate = approvalDate;
    }
    
    public List<Order> LoadMyApprovedOrders()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<Order>) sc.LoadMyApprovedOrders(AuthenticationSettings.getCurrentUserID());
    }
    
    public List<Order> LoadAllOrders()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<Order>) sc.LoadAllOrders();
    }

    @Override
    public String toString()
    {
        return "Order{" + "orderID=" + orderID + ", staffOrder=" + staffOrder + ", approvalDate=" + approvalDate + '}';
    }

    
}
