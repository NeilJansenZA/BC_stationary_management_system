/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteClient;

import ApplicationHelper.Helper;
import BusinessModule.*;
import bc_stationarymanagementsystem_rmiserver.IRemoteConnection;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil
 */
public class ServerConnection<T> implements Serializable
{
    protected static ServerConnection sc;
    protected Registry reg;
    protected IRemoteConnection remoteMethod;
    private List<Object[]> objectList;
    private Object[] object;
    private List<T> readList;
    private T readSingle;
    private final java.util.Date date = new java.util.Date();
    
    public T GetAllProductFromID(int productID)
    {
        Reset();
        try
        {
            object = remoteMethod.GetAllProductFromID(productID);
            
            readSingle = (T) new StationaryStock(Integer.parseInt(object[0].toString()), object[1].toString(),
                        Integer.parseInt(object[2].toString()), object[3].toString(), Double.parseDouble(object[4].toString()),
                        Integer.parseInt(object[5].toString()), (java.sql.Date) object[6]);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    public String GetProductFromID(int productID)
    {
        Reset();
        String name = "";
        try
        {
            name = remoteMethod.GetProductFromID(productID);
        }
        catch(Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
      
        return name;
    }
    
    public void DeleteStockItem(int stationaryStockID)
    {
        Reset();
        try
        {
            remoteMethod.DeleteStockItem(stationaryStockID);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public List<T> LoadStationaryStock()
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadStationaryStock();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new StationaryStock(Integer.parseInt(singleObject[0].toString()), singleObject[1].toString(),
                        Integer.parseInt(singleObject[2].toString()), singleObject[3].toString(), Double.parseDouble(singleObject[4].toString()),
                        Integer.parseInt(singleObject[5].toString()), (java.sql.Date) singleObject[6]));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public void InsertStationaryStock(StationaryStock stock)
    {
        Reset();
        try
        {
            object = new Object[6];
            object[0] = stock.getProductName();
            object[1] = stock.getStationaryCategoryID();
            object[2] = stock.getModel();
            object[3] = stock.getPrice();
            object[4] = stock.getQuantity();
            object[5] = new java.sql.Date(date.getTime());
            
            remoteMethod.InsertStationaryStock(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public T GetStationaryCategory(int stationaryCategoryID)
    {
        Reset();
        try
        {
            object = remoteMethod.GetStationaryCategory(stationaryCategoryID);
            
            readSingle = (T) new StationaryCategory(Integer.parseInt(object[0].toString()), object[1].toString());
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    public List<T> LoadAllPendingOrders()
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadAllPendingOrders();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new StaffOrder(Integer.parseInt(singleObject[0].toString()), singleObject[1].toString(), (java.sql.Date) singleObject[2],
                        Double.parseDouble(singleObject[3].toString()),singleObject[4].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public List<T> LoadMyPendingOrders(int currentUserID)
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadMyPendingOrders(currentUserID);
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new StaffOrder(singleObject[0].toString(), (java.sql.Date) singleObject[1],
                        Double.parseDouble(singleObject[2].toString()),singleObject[3].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public List<T> LoadPurchaseOrders()
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadPurchaseOrders();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new PurchaseOrder(singleObject[0].toString(), Integer.parseInt(singleObject[1].toString()), (java.sql.Date)singleObject[2],
                        Integer.parseInt(singleObject[3].toString()), Double.parseDouble(singleObject[4].toString())));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public void UpdateAndRemovePurchaseOrder(PurchaseOrder purchaseOrder, int stockQuantity)
    {
        Reset();
        try
        {
            object = new Object[2];
            object[0] = purchaseOrder.getStationaryStockID();
            object[1] = purchaseOrder.getPurchaseOrderID();
            
            remoteMethod.UpdateAndRemovePurchaseOrder(object, stockQuantity);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public void InsertPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        Reset();
        try
        {
            object = new Object[3];
            object[0] = purchaseOrder.getPurchaseOrderID();
            object[1] = purchaseOrder.getStationaryStockID();
            object[2] = new java.sql.Date(purchaseOrder.getPurchaseDate().getTime());
            
            remoteMethod.InsertPurchaseOrder(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public List<T> LoadAllOrders()
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadAllOrders();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new Order(singleObject[0].toString(), new StaffOrder(Integer.parseInt(singleObject[1].toString()), 
                        singleObject[2].toString(), (java.sql.Date)singleObject[3], Double.parseDouble(singleObject[4].toString())),
                        (java.sql.Date)singleObject[5]));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    } 
    
    public List<T> LoadMyApprovedOrders(int currentUserID)
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadMyApprovedOrders(currentUserID);
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new Order(singleObject[0].toString(), new StaffOrder(singleObject[1].toString(), (java.sql.Date)singleObject[2],
                        Double.parseDouble(singleObject[3].toString())), (java.sql.Date)singleObject[4]));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public T GetDepartment(int departmentID)
    {
        Reset();
        try
        {
            object = remoteMethod.GetDepartment(departmentID);
            
            readSingle = (T) new Department(Integer.parseInt(object[0].toString()), object[1].toString());
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    public T GetStaffOrderID(String staffStockID)
    {
        Reset();
        try
        {
            object = remoteMethod.GetStaffOrderID(staffStockID);
            
            readSingle = (T) new StaffOrder(Integer.parseInt(object[0].toString()), Integer.parseInt(object[1].toString()), object[2].toString(), 
                    (java.sql.Date)object[3], Double.parseDouble(object[4].toString()), object[5].toString());
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    public List<T> GetOrderDetails(String staffStockID)
    {
        Reset();
        try
        {
            objectList = remoteMethod.GetOrderDetails(staffStockID);
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new StaffStockOrder(Integer.parseInt(singleObject[0].toString()), Integer.parseInt(singleObject[1].toString()),
                        Double.parseDouble(singleObject[2].toString())));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
        
    public void DenyOrder(StaffOrder staffOrder)
    {
        Reset();
        try
        {
            object = new Object[2];
            object[0] = staffOrder.getOrderStatus();
            object[1] = staffOrder.getStaffOrderID();
            
            remoteMethod.DenyOrder(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public void UpdateStockEntry(StationaryStock stationaryStock)
    {
        Reset();
        try
        {
            object = new Object[7];
            object[0] = stationaryStock.getStationaryStockID();
            object[1] = stationaryStock.getProductName();
            object[2] = stationaryStock.getStationaryCategoryID();
            object[3] = stationaryStock.getModel();
            object[4] = stationaryStock.getPrice();
            object[5] = stationaryStock.getQuantity();
            object[6] = new java.sql.Date(date.getTime());
            
            remoteMethod.UpdateStockEntry(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public void InsertOrder(Order order)
    {
        Reset();
        try
        {
            object = new Object[3];
            object[0] = order.getOrderID();
            object[1] = order.getStaffOrder().getStaffOrderID();
            object[2] = new java.sql.Date(order.getApprovalDate().getTime());
            
            Object[] objectTwo = new Object[2];
            objectTwo[0] = order.getStaffOrder().getOrderStatus();
            objectTwo[1] = order.getStaffOrder().getStaffOrderID();
            
            remoteMethod.InsertOrder(object, objectTwo);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public void RequestCurrentOrder()
    {
        Reset();
        try
        {
            for (StaffStockOrder stock : Authentication.ActiveAccess.CurrentOrder.getOrderStock())
            {
                object = new Object[4];
                object[0] = Authentication.ActiveAccess.CurrentOrder.getStaffStockID();
                object[1] = stock.getStationaryStockID();
                object[2] = stock.getQuantity();
                object[3] = stock.getPrice();
                
                objectList.add(object);
            }
            
            object = new Object[5];
            object[0] = Authentication.ActiveAccess.CurrentOrder.getStaffID();
            object[1] = Authentication.ActiveAccess.CurrentOrder.getStaffStockID();
            object[2] = new java.sql.Date(Authentication.ActiveAccess.CurrentOrder.getCurrentDate().getTime());
            object[3] = Authentication.ActiveAccess.CurrentOrder.getOrderTotal();
            object[4] = "Pending Approval";
            
            remoteMethod.RequestCurrentOrder(objectList, object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public T GetCampus(int campusID)
    {
        Reset();
        try
        {
            object = remoteMethod.GetCampus(campusID);

            readSingle = (T) new Campus(Integer.parseInt(object[0].toString()), object[1].toString(), object[2].toString(),
                    object[3].toString(), object[4].toString());
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    public void InsertStaffRegistration(Staff staff)
    {
        Reset();
        try
        {
            object = new Object[8];
            object[0] = staff.getStaffName();
            object[1] = staff.getStaffSurname();
            object[2] = staff.getStaffCampusID();
            object[3] = staff.getStaffDepartment();
            object[4] = staff.getStaffCellNo();
            object[5] = staff.getStaffEmail();
            object[6] = staff.getStaffUsername();
            object[7] = staff.getStaffPassword();
            
            remoteMethod.InsertStaffRegistration(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public void UpdateStaffEntry(Staff staff)
    {
        Reset();
        try
        {
            object = new Object[9];
            object[0] = staff.getStaffID();
            object[1] = staff.getStaffName();
            object[2] = staff.getStaffSurname();
            object[3] = staff.getStaffCampusID();
            object[4] = staff.getStaffDepartment();
            object[5] = staff.getStaffCellNo();
            object[6] = staff.getStaffEmail();
            object[7] = staff.getStaffUsername();
            object[8] = staff.getStaffPassword();
            
            remoteMethod.UpdateStaffEntry(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public int CheckUsername(String username)
    {
        Reset();
        int check = 0;
        try
        {
            check = remoteMethod.CheckUsername(username);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return check;
    }
    
    public T ReadStaffMemberOrder(int staffOrderID)
    {
        Reset();
        try
        {
            object = remoteMethod.ReadStaffMemberOrder(staffOrderID);
            
            readSingle = (T) new Staff(Integer.parseInt(object[0].toString()), object[1].toString(), object[2].toString(), Integer.parseInt(object[3].toString()), 
                    Integer.parseInt(object[4].toString()), object[5].toString(), object[6].toString(), object[7].toString(), object[8].toString());
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    public List<T> LoadRegisterRequests()
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadRegisterRequests();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new Staff(Integer.parseInt(singleObject[0].toString()), (Date) singleObject[1], singleObject[2].toString(),
                        singleObject[3].toString(), Integer.parseInt(singleObject[4].toString()), Integer.parseInt(singleObject[5].toString()),
                        singleObject[6].toString(), singleObject[7].toString(), singleObject[8].toString(), singleObject[9].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public void DeleteRegistry(int registerID)
    {
        Reset();
        try
        {
            remoteMethod.DeleteRegistry(registerID);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public List<T> LoadUsers()
    {
        Reset();
        try
        {
            objectList = remoteMethod.LoadUsers();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new Staff(Integer.parseInt(singleObject[0].toString()), singleObject[1].toString(), singleObject[2].toString(), Integer.parseInt(singleObject[3].toString()), 
                    Integer.parseInt(singleObject[4].toString()), singleObject[5].toString(), singleObject[6].toString(), singleObject[7].toString(), singleObject[8].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public void InsertStaff(Staff staff)
    {
        Reset();
        try
        {
            object = new Object[8];
            object[0] = staff.getStaffName();
            object[1] = staff.getStaffSurname();
            object[2] = staff.getStaffCampusID();
            object[3] = staff.getStaffDepartment();
            object[4] = staff.getStaffCellNo();
            object[5] = staff.getStaffEmail();
            object[6] = staff.getStaffUsername();
            object[7] = staff.getStaffPassword();
            
            remoteMethod.InsertStaff(object);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
    
    public List<T> ReadDepartment()
    {
        Reset();
        try
        {
            objectList = remoteMethod.ReadDepartment();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new Department(Integer.parseInt(singleObject[0].toString()), singleObject[1].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public List<T> ReadCampus()
    {       
        Reset();
        try
        {
            objectList = remoteMethod.ReadCampus();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new Campus(Integer.parseInt(singleObject[0].toString()), singleObject[1].toString(), singleObject[2].toString(), 
                        singleObject[3].toString(), singleObject[4].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public List<T> ReadStationaryCategory()
    {
        Reset();
        try
        {
            objectList = remoteMethod.ReadStationaryCategory();
            
            for (Object[] singleObject : objectList)
            {
                readList.add((T) new StationaryCategory(Integer.parseInt(singleObject[0].toString()), singleObject[1].toString()));
            }
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readList;
    }
    
    public T ReadStaffMember(int currentUserID)
    {
        Reset();
        try
        {
            object = remoteMethod.ReadStaffMember(currentUserID);
            
            readSingle = (T) new Staff(Integer.parseInt(object[0].toString()), object[1].toString(), object[2].toString(), Integer.parseInt(object[3].toString()), 
                    Integer.parseInt(object[4].toString()), object[5].toString(), object[6].toString(), object[7].toString(), object[8].toString());
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return readSingle;
    }
    
    private void Reset()     
    {
        readSingle = null;
        readList = new ArrayList<>();
        object = null;
        objectList = new ArrayList<>();
    }
    
    public int Login(String username, String password)
    {
        int login = 0;
        try
        {
            login = remoteMethod.Login(username, password);
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        
        return login;
    }
    
    private ServerConnection()
    {
        ConnectToServer();
    }
    
    protected void ConnectToServer()
    {
        try
        {
            reg = LocateRegistry.getRegistry(1099);
            remoteMethod = (IRemoteConnection)Naming.lookup("ServerConnection");
            Helper.DisplayError("Server connection successfully established!", "Server Connection Successfull");
        } 
        catch (RemoteException | MalformedURLException | NotBoundException | NullPointerException ex)
        {
            Helper.DisplayError("Error connecting to server : " + ex.toString(), "Error Establishing Connection To Server -- PORT 1099");
        }
    }
    
    public static ServerConnection GetInstance()
    {
        if(null == sc)
        {
            sc = new ServerConnection();
            return sc;
        }
        else
        {
            return sc;
        }
    }
}
