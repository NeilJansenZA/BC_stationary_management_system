/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import BusinessModule.CurrentOrder;
import BusinessModule.StaffStockOrder;
import BusinessModule.StationaryStock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class ActiveAccess
{
    private static StationaryStock CurrentStock;
    public static List<StaffStockOrder> CurrentOrderList = new ArrayList<>();
    public static CurrentOrder CurrentOrder;

    public static CurrentOrder getCurrentOrder()
    {
        return CurrentOrder;
    }

    public static void setCurrentOrder(CurrentOrder currentOrder)
    {
        ActiveAccess.CurrentOrder = currentOrder;
    }
    
    public static StationaryStock getCurrentStock()
    {
        return CurrentStock;
    }

    public static void setCurrentStock(StationaryStock aCurrentStock)
    {
        CurrentStock = aCurrentStock;
    }

    public static List<StaffStockOrder> getCurrentOrderList()
    {
        return CurrentOrderList;
    }
}
