/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BusinessModule.StockComporators;

import BusinessModule.StationaryStock;
import java.util.Comparator;

/**
 *
 * @author Student
 */
public class StockDate_ClosestToFurthest implements Comparator{
    
    @Override
    public int compare(Object o1, Object o2) {
        StationaryStock ss1 = (StationaryStock) o1;
        StationaryStock ss2 = (StationaryStock) o2;
        
        if(ss1.getDateOfEntryUpdate().before(ss2.getDateOfEntryUpdate()))
        {
            return -1;
        }
        else if(ss1.getDateOfEntryUpdate().after(ss2.getDateOfEntryUpdate()))
        {
            return 1;
        }
        else 
        {
            return 0;
        }      
    }
}
