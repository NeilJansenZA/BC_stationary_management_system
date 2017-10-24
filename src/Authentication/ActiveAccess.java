/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import BusinessModule.StaffStockOrder;
import BusinessModule.StationaryStock;
import java.util.List;

/**
 *
 * @author Neil
 */
public class ActiveAccess
{
    private static StationaryStock CurrentStock;
    private static List<StaffStockOrder> CurrentOrderList;
    
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

    public static void setCurrentOrderList(List<StaffStockOrder> aCurrentOrderList)
    {
        CurrentOrderList = aCurrentOrderList;
    }
}
