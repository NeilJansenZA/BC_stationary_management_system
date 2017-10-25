/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessModule.StaffOrderComparators;

import BusinessModule.StaffOrder;
import java.util.Comparator;

/**
 *
 * @author Student
 */
public class StaffOrderDate_ClosestToFurthest implements Comparator{
    
    @Override
    public int compare(Object o1, Object o2) {
        StaffOrder or1 = (StaffOrder) o1;
        StaffOrder or2 = (StaffOrder) o2;
        
        if(or1.getOrderDate().before(or2.getOrderDate()))
        {
            return -1;
        }
        else if(or1.getOrderDate().after(or2.getOrderDate()))
        {
            return 1;
        }
        else 
        {
            return 0;
        }          
    }
    
}
