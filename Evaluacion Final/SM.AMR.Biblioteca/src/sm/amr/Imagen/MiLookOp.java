/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.Imagen;

import java.awt.image.ByteLookupTable;
import java.awt.image.LookupTable;

/**
 *
 * @author antonio
 */
public class MiLookOp {
    private byte[] lt;
    
    /**
     * Creacion del objeto MiLookOp
     */
    public MiLookOp ()
    {
        lt = new byte[256];

        for (int l = 0; l <= 255; ++l)
        {
            // ILUMINAR OSCUROS SOLO
            if(l<50)
            {
                lt[l] = (byte) (l+30);
            }
            else
            {
                lt[l] = (byte) l;
            }
        }
    }
    
    /**
     * Devuelveel array de byte byte[]
     * @return LookupTable
     */
    public LookupTable getTable()
    {
        return(new ByteLookupTable(0, lt));
    }
    
}
