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
public class Department
{   
    private int departmentID;
    private String departmentName;

    public Department(int departmentID, String departmentName)
    {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }
    
    public Department GetDepartment(int departmentID)
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (Department) sc.GetDepartment(departmentID);
    }
    
    public Department()
    {
        
    }
    
    public List<Department> ReadDepartment()
    {
        ServerConnection sc = ServerConnection.GetInstance();
        return (List<Department>) sc.ReadDepartment();
    }

    public int getDepartmentID()
    {
        return departmentID;
    }

    public void setDepartmentID(int departmentID)
    {
        this.departmentID = departmentID;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }
    
    
}
