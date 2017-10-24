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
public class Order
{
    private DataConnection dc = new DataConnection();
    private String orderID;
    private StaffOrder staffOrder;
    private Date approvalDate;

    public Order()
    {
        this.dc = new DataConnection();
    }

    public Order(String orderID, StaffOrder staffOrder, Date approvalDate)
    {
        this.dc = new DataConnection();
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
        this.dc = new DataConnection();
        return (List<Order>) dc.LoadMyApprovedOrders();
    }
}
