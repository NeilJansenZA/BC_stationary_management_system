/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import BusinessModule.StationaryStock;

/**
 *
 * @author Neil
 */
public class ActiveAccess
{
    private static StationaryStock CurrentStock;

    public static StationaryStock getCurrentStock()
    {
        return CurrentStock;
    }

    public static void setCurrentStock(StationaryStock aCurrentStock)
    {
        CurrentStock = aCurrentStock;
    }
}
