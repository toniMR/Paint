/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.Imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import sm.image.BufferedImageOpAdapter;
import sm.image.BufferedImagePixelIterator;
import sm.image.BufferedImagePixelIterator.PixelData;

/**
 *
 * @author antonio
 */
public class SepiaOp extends BufferedImageOpAdapter{
    /**
     * Crea el objeto SepiaOp
     */
    public SepiaOp () {
    }
    
    /**
     * Hace la operación Sepia pixel a pixel
     * @param src BufferedImage fuente
     * @param dest BufferedImage destino
     * @return BufferedImage
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
                int[] pixelComp=null;
                pixelComp = srcRaster.getPixel(x, y, pixelComp);
                
                pixelComp[0] = (int) Math.min(255, 0.393 * pixelComp[0] + 0.769 * pixelComp[1] + 0.189 * pixelComp[2]);
                pixelComp[1] = (int) Math.min(255, 0.349 * pixelComp[0] + 0.686 * pixelComp[1] + 0.168 * pixelComp[2]);
                pixelComp[2] = (int) Math.min(255, 0.272 * pixelComp[0] + 0.534 * pixelComp[1] + 0.131 * pixelComp[2]);
                
                destRaster.setPixel(x, y, pixelComp);
            }
        }
        return dest;
    }
}
