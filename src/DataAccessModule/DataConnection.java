/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessModule;

import ApplicationHelper.Helper;
import Authentication.AuthenticationSettings;
import BusinessModule.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 * @param <T>
 */
public class DataConnection<T>
{
    private String query = "";
    private List<T> readList;
    private T readSingle;
    Connection con = null;
    CallableStatement cs = null;
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    private final java.util.Date date = new java.util.Date();

    public DataConnection(){}
    
    public void InsertStationaryStock(StationaryStock stationaryStock)
    {
        ConnectToDB();
        try
        {
            query = "CALL proc_InsertStationaryStock(?,?,?,?,?,?)";
            cs = con.prepareCall(query);
            
            cs.setString(1, stationaryStock.getProductName());
            cs.setInt(2, stationaryStock.getStationaryCategoryID());
            cs.setString(3, stationaryStock.getModel());
            cs.setDouble(4, stationaryStock.getPrice());
            cs.setInt(5, stationaryStock.getQuantity());
            cs.setDate(6, new java.sql.Date(date.getTime()));
            
            cs.execute();
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
    }
    
    public List<T> LoadStationaryStock()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblstationarystock";
            st = con.createStatement();
            
            rs = st.executeQuery(query);
            
            while (rs.next())
            {
                readList.add((T) new StationaryStock(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getDate(7)));
            }
            
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readList;
        }
    }
    
    public List<T> ReadStationaryCategory()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblstationarycategory";
            st = con.createStatement();
            
            rs = st.executeQuery(query);
            
            while(rs.next())
            {
                readList.add((T) new StationaryCategory(rs.getInt(1), rs.getString(2)));
            }
            
            con.close();          
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readList;
        }
    }
    
    public T GetStationaryCategory(int stationaryCategoryID)
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblstationarycategory WHERE stationaryCategoryID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, stationaryCategoryID);            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                readSingle = (T) new StationaryCategory(rs.getInt(1), rs.getString(2));        
            }
                     
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readSingle;
        }
    }
    
    public void DeleteRegistry(int registerID)
    {
        ConnectToDB();
        try
        {
            query = "DELETE FROM tblstaffregistration WHERE staffRegistrationID = ?";
            ps = con.prepareStatement(query);
            
            ps.setInt(1, registerID);
            
            ps.execute();
            
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
    }
    
    public T GetCampus(int campusID)
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblcampus WHERE campusID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, campusID);            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                readSingle = (T) new Campus(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));        
            }
                     
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readSingle;
        }
    }
    
    public T GetDepartment(int departmentID)
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tbldepartment WHERE departmentID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, departmentID);            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                readSingle = (T) new Department(rs.getInt(1), rs.getString(2));        
            }
                     
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readSingle;
        }
    }
    
    public List<T> LoadUsers()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblstaff";
            st = con.createStatement();
            
            rs = st.executeQuery(query);
            
            while (rs.next())
            {
                readList.add((T) new Staff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readList;
        }
    }
    
    public List<T> LoadRegisterRequests()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblstaffregistration";
            st = con.createStatement();
            
            rs = st.executeQuery(query);
            
            while (rs.next())
            {
                readList.add((T) new Staff(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
            
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readList;
        }
    }
    
    public void UpdateStaffEntry(Staff staff)
    {
        ConnectToDB();
        try
        {
            query = "CALL proc_UpdateStaff(?,?,?,?,?,?,?,?,?)";
            cs = con.prepareCall(query);
            
            cs.setInt(1, staff.getStaffID());
            cs.setString(2, staff.getStaffName());
            cs.setString(3, staff.getStaffSurname());
            cs.setInt(4, staff.getStaffCampusID());
            cs.setInt(5, staff.getStaffDepartment());
            cs.setString(6, staff.getStaffCellNo());
            cs.setString(7, staff.getStaffEmail());
            cs.setString(8, staff.getStaffUsername());
            cs.setString(9, staff.getStaffPassword());
            
            cs.execute();
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
    }
    
    public T ReadStaffMember()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblstaff WHERE staffID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, Authentication.AuthenticationSettings.getCurrentUserID());            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                readSingle = (T) new Staff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));          
            }
                     
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readSingle;
        }
    }  
    
    public List<T> ReadDepartment()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblDepartment";
            st = con.createStatement();
            
            rs = st.executeQuery(query);
            
            while(rs.next())
            {
                readList.add((T) new Department(rs.getInt(1), rs.getString(2)));
            }
            
            con.close();          
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readList;
        }
    }

    public List<T> ReadCampus()
    {
        ConnectToDB();
        try
        {
            query = "SELECT * FROM tblcampus";
            Statement st = con.createStatement();
            
            rs = st.executeQuery(query);
            
            while(rs.next())
            {
                readList.add((T) new Campus(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            
            con.close();          
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
        finally
        {
            return readList;
        }
    }
    
    public int CheckUsername(String username)
    {
        int validUsername = -1;
        ConnectToDB();
        try
        {
            query = "CALL proc_CheckUsername(?, ?)";
            cs = con.prepareCall(query);
            
            cs.setString(1, username);
            cs.registerOutParameter(2, Types.INTEGER);
            
            cs.executeQuery();
            
            validUsername = cs.getInt(2);
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
            return validUsername;
        }
        finally
        {
            return validUsername;
        }
    }
    
    public void InsertStaff(Staff staff)
    {
        ConnectToDB();
        try
        {
            query = "CALL sp_InsertStaff(?, ?, ?, ?, ?, ?, ?, ?)";
            cs = con.prepareCall(query);
            
            cs.setString(1, staff.getStaffName());
            cs.setString(2, staff.getStaffSurname());
            cs.setInt(3, staff.getStaffCampusID());
            cs.setInt(4, staff.getStaffDepartment());
            cs.setString(5, staff.getStaffCellNo());
            cs.setString(6, staff.getStaffEmail());
            cs.setString(7, staff.getStaffUsername());
            cs.setString(8, staff.getStaffPassword());
            
            cs.execute();
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
    }
    public void InsertStaffRegistration(Staff staff)
    {
        ConnectToDB();
        try
        {
            query = "CALL sp_InsertStaffRegistration(?,?,?,?,?,?,?,?,?)";
            cs = con.prepareCall(query);
            
            cs.setDate(1, new java.sql.Date(date.getTime()));
            cs.setString(2, staff.getStaffName());
            cs.setString(3, staff.getStaffSurname());
            cs.setInt(4, staff.getStaffCampusID());
            cs.setInt(5, staff.getStaffDepartment());
            cs.setString(6, staff.getStaffCellNo());
            cs.setString(7, staff.getStaffEmail());
            cs.setString(8, staff.getStaffUsername());
            cs.setString(9, staff.getHashPassword());
            
            cs.execute();
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
    }
    
    public int Login(String username, String password)
    {
        int loginType = -1;
        ConnectToDB();
        try
        {
            query = "CALL proc_CheckLogin(?, ?, ?)";
            cs = con.prepareCall(query);

            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.INTEGER);
            
            cs.execute();
            
            loginType = cs.getInt(3);
            con.close();
        }
        catch (SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
            return loginType;
        }
        catch (Exception exx)
        {
            Helper.DisplayError(exx.toString(), "Database Error");
        }
        finally
        {
            return loginType;
        }
    }
    
    private void ConnectToDB()
    {
        readSingle = null;
        rs = null;
        readList = new ArrayList<>();
        try
        {    
            con = DriverManager.getConnection(AuthenticationSettings.getConnectionString(), AuthenticationSettings.getConnectionUsername(), AuthenticationSettings.getConnectionPassword());
        }
        catch(SQLException ex)
        {
            Helper.DisplayError(ex.toString(), "Database Error");
        }
    }
}
