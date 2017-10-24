/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessModule.OrderDetailsComparators;

import BusinessModule.StaffStockOrder;
import java.util.Comparator;

/**
 *
 * @author Student
 */
public class OrderDetailsPrice_LowestToHighest implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        StaffStockOrder sso1 = (StaffStockOrder) o1;
        StaffStockOrder sso2 = (StaffStockOrder) o2;
        
        return (int) (sso1.getPrice() - sso2.getPrice());
    }
    
}
