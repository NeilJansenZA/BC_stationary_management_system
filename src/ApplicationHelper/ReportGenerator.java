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
        try
        {
            String fileName = new Date().getTime() + "" + SecurityModule.IDCreation.CreateOrderID() + listData.getClass().getSimpleName() + ".txt";
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
        try
        {
            String fileName = new Date().getTime() + "" + SecurityModule.IDCreation.CreateOrderID() + data.getClass().getSimpleName() + ".txt";
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            pw.println(data.toString());
            pw.close();
        } catch (IOException ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }
}
