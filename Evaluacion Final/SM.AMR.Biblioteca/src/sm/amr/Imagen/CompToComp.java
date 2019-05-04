/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.Imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author antonio
 */
public class CompToComp extends BufferedImageOpAdapter{
    /**
     * Creacion del Objeto CompToComp
     */
    public CompToComp () {
    }
    
    /**
     * Le aplica la operacion Componente a Componente a la imagen indicada
     * @param src BufferedImage destino
     * @param dest BufferedImage fuente
     * @return 
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest)
    {
        if (src == null)
        {
            throw new NullPointerException("src image is null");
        }
        if (dest == null)
        {
            dest = createCompatibleDestImage(src, null);
        }
        else
        {
            //Comprobar si es compatible
        }
        
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        
        for (int x = 0; x < srcRaster.getWidth(); x++) 
        {
            for(int y = 0; y < srcRaster.getHeight(); y++)
            {
                for(int band = 0; band < srcRaster.getNumBands(); band++)
                {
                    int sample = srcRaster.getSample(x, y, band);
                    double num_digitos = Integer.toString(sample).length();
                    int ultimo_digito = (sample%((int)Math.pow(10.0, num_digitos-1)));
                    sample = Math.min(sample+(50-ultimo_digito),255);
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    }
    
}
