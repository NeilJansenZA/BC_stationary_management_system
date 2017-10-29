/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil
 * @param <T>
 */
public class ReportGenerator<T>
{

    public ReportGenerator(List<T> listData)
    {
        final Class<?> classType = listData.iterator().next().getClass();
        final String className = classType.getSimpleName();
        StringBuilder reportBuilder = new StringBuilder();
        
        if(className.equalsIgnoreCase("StationaryStock"))
        {
            reportBuilder.append("Stationary Stock");
        }
        else
        {
            reportBuilder.append("All Orders");
        }
        
        try
        {
            reportBuilder.append("_").append(new java.sql.Date(new Date().getTime()));
            String fileName = reportBuilder + ".txt";
            Helper.DisplayError(fileName);
                    
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));

            for (T line : listData)
            {
                pw.println(line.toString());
            }
            pw.close();
        } catch (IOException ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }

    public ReportGenerator(T data)
    {
        final Class<?> classType = data.getClass();
        StringBuilder reportBuilder = new StringBuilder();
       
        try
        {
            String username = (String)classType.getMethod("getStaffUsername").invoke(data);
            String orderID = (String)classType.getMethod("getOrderID").invoke(data);
            Date orderDate = (Date)classType.getMethod("getApprovalDate").invoke(data);
            reportBuilder.append(username).append("_").append(orderID).append("_").append(orderDate);
            
            String fileName = reportBuilder + ".txt";
            Helper.DisplayError(fileName);
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            pw.println(data.toString());
            pw.close();
        } catch (IOException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
}
