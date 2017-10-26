/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModule;

import DataAccessModule.DataConnection;
import static SecurityModule.EncryptionSource.GenerateHash;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Staff
{
    private DataConnection dc = new DataConnection();;
    
    private int staffID;
    private String staffName;
    private String staffSurname;
    private int staffCampusID;
    private int staffDepartment;
    private String staffCellNo;
    private String staffEmail;
    private String staffUsername;
    private String staffPassword;
    protected String hashPassword;
    
    private Date registerRequestDate;
    private int registerID;

    public Staff(int registerID, Date registerRequestDate, String staffName, String staffSurname, int staffCampusID, int staffDepartment, String staffCellNo, String staffEmail, String staffUsername, String staffPassword)
    {
        this.dc = new DataConnection();
        this.registerID = registerID;
        this.registerRequestDate = registerRequestDate;
        this.staffName = staffName;
        this.staffSurname = staffSurname;
        this.staffCampusID = staffCampusID;
        this.staffDepartment = staffDepartment;
        this.staffCellNo = staffCellNo;
        this.staffEmail = staffEmail;
        this.staffUsername = staffUsername;
        this.staffPassword = staffPassword;  
    }

    public Staff()
    {
    }
    
    public Staff(String staffName, String staffSurname, int staffCampusID, int staffDepartment, String staffCellNo, String staffEmail, String staffUsername, String staffPassword)
    {
        this.dc = new DataConnection();
        this.staffName = staffName;
        this.staffSurname = staffSurname;
        this.staffCampusID = staffCampusID;
        this.staffDepartment = staffDepartment;
        this.staffCellNo = staffCellNo;
        this.staffEmail = staffEmail;
        this.staffUsername = staffUsername;
        this.staffPassword = staffPassword;
    }

    public Staff(int staffID, String staffName, String staffSurname, int staffCampusID, int staffDepartment, String staffCellNo, String staffEmail, String staffUsername, String staffPassword)
    {
        this.dc = new DataConnection();
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffSurname = staffSurname;
        this.staffCampusID = staffCampusID;
        this.staffDepartment = staffDepartment;
        this.staffCellNo = staffCellNo;
        this.staffEmail = staffEmail;
        this.staffUsername = staffUsername;
        this.staffPassword = staffPassword;
    }

    public int getStaffID()
    {
        return staffID;
    }

    public void setStaffID(int staffID)
    {
        this.staffID = staffID;
    }

    public String getStaffName()
    {
        return staffName;
    }

    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }

    public String getStaffSurname()
    {
        return staffSurname;
    }

    public void setStaffSurname(String staffSurname)
    {
        this.staffSurname = staffSurname;
    }

    public int getStaffCampusID()
    {
        return staffCampusID;
    }

    public void setStaffCampusID(int staffCampusID)
    {
        this.staffCampusID = staffCampusID;
    }

    public int getStaffDepartment()
    {
        return staffDepartment;
    }

    public void setStaffDepartment(int staffDepartment)
    {
        this.staffDepartment = staffDepartment;
    }

    public String getStaffCellNo()
    {
        return staffCellNo;
    }

    public void setStaffCellNo(String staffCellNo)
    {
        this.staffCellNo = staffCellNo;
    }

    public String getStaffEmail()
    {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail)
    {
        this.staffEmail = staffEmail;
    }

    public String getStaffUsername()
    {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername)
    {
        this.staffUsername = staffUsername;
    }

    public String getStaffPassword()
    {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword)
    {
        this.staffPassword = staffPassword;
    }
    
    public String getHashPassword()
    {   
        setHashPassword();
        return hashPassword;
    }

    public void setHashPassword()
    {
        this.hashPassword = GenerateHash(this.staffPassword);
    }
    
    public void InsertStaffRegistration()
    {
        dc.InsertStaffRegistration(this);
    }
    
    public List<Staff> LoadUsers()
    {
        return (List<Staff>) dc.LoadUsers();
    }
    
    public void InsertStaff()
    {
        dc.InsertStaff(this);
        DeleteRegistry();
    }
    
    public void UpdateStaffEntry()
    {
        dc.UpdateStaffEntry(this);
    }
    
    public boolean CheckUsername(String username)
    {
        int validUsername = dc.CheckUsername(username);
        boolean valid = false;
        
        switch(validUsername)
        {
            case -1:
                valid = false;
                break;
                
            case 1:
                valid = true;
                break;
                
            case 0:
                valid = false;
                break;
        }
        
        return valid;
    }
    
    public Staff(String staffUsername, String staffPassword)
    {
        this.dc = new DataConnection();
        this.staffUsername = staffUsername;
        this.staffPassword = staffPassword;
    }
    
    public Staff ReadStaffMember()
    {
        return (Staff) dc.ReadStaffMember();
    }
    
    public Staff ReadStaffMemberOrder(int orderStaffID)
    {
        return (Staff) dc.ReadStaffMemberOrder(orderStaffID);
    }
    
    public void DeleteRegistry()
    {
        dc.DeleteRegistry(this.registerID);
    }

    @Override
    public String toString()
    {
        return "Staff{" + ", staffName=" + staffName + ", staffSurname=" + staffSurname + ", staffCampusID=" + staffCampusID + ", staffDepartment=" + staffDepartment + ", staffCellNo=" + staffCellNo + ", staffEmail=" + staffEmail + ", staffUsername=" + staffUsername + ", staffPassword=" + staffPassword + ", hashPassword=" + hashPassword + ", registerRequestDate=" + registerRequestDate + '}';
    }
    
    public List<Staff> LoadRegisterRequests()
    {
        return (List<Staff>) dc.LoadRegisterRequests();
    }

    public Date getRegisterRequestDate()
    {
        return registerRequestDate;
    }

    public void setRegisterRequestDate(Date registerRequestDate)
    {
        this.registerRequestDate = registerRequestDate;
    }

    public int getRegisterID()
    {
        return registerID;
    }

    public void setRegisterID(int registerID)
    {
        this.registerID = registerID;
    }
}
