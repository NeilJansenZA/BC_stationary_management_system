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
public class StaffOrderTotal_LowestToHighest implements Comparator{
    
    @Override
    public int compare(Object o1, Object o2) {
        StaffOrder or1 = (StaffOrder) o1;
        StaffOrder or2 = (StaffOrder) o2;
        
        return (int) (or1.getOrderTotal() - or2.getOrderTotal());
    }
    
}
