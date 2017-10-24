/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessModule.StockComporators;

import BusinessModule.Order;
import BusinessModule.StationaryStock;
import java.util.Comparator;

/**
 *
 * @author Student
 */
public class StockDate_ClosestToFurthest implements Comparator{
    
    @Override
    public int compare(Object o1, Object o2) {
        Order or1 = (Order) o1;
        Order or2 = (Order) o2;
        
        if(or2.getStaffOrder().getOrderDate().before(or1.getStaffOrder().getOrderDate()))
        {
            return -1;
        }
        else if(or2.getStaffOrder().getOrderDate().after(or1.getStaffOrder().getOrderDate()))
        {
            return 1;
        }
        else 
        {
            return 0;
        }          
    }
}
