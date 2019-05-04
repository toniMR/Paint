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
public class PixelToPixel extends BufferedImageOpAdapter{
    
    /**
     * Crea el objeto PixeltoPixel
     */
    public PixelToPixel () {
    }
    
    /**
     * Aplica el efecto pixel a pixel
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
                
                pixelComp[0] = (int) Math.min(255, Math.sqrt(pixelComp[0])*9+ Math.sqrt(pixelComp[0])*4 + Math.sqrt(pixelComp[0])*3);
                pixelComp[1] = (int) Math.min(255, Math.sqrt(pixelComp[0])*9+ Math.sqrt(pixelComp[0])*3 + Math.sqrt(pixelComp[0])*4);
                pixelComp[2] = (int) Math.min(255, Math.sqrt(pixelComp[0])*4+ Math.sqrt(pixelComp[0])*9 + Math.sqrt(pixelComp[0])*3);
                
                destRaster.setPixel(x, y, pixelComp);
            }
        }
        return dest;
    }
    
}
