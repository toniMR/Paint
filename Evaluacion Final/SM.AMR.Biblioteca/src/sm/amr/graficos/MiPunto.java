/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author antonio
 */
public class MiPunto extends MiForma
{
    /**
     * Crea un MiPunto
     * @param p donde lo crea
     */
    public MiPunto (Point2D p)
    {
        super.shape = new Line2D.Double (p,p);       
    }
    
    /**
     * Devuelve si un punto está cerca del punto
     * @param p punto a comprobar si es cercano
     * @return boolean
     */
    public boolean isNear(Point2D p)
    {
        boolean cercano = false;
        
        double p1x = ((Line2D)shape).getX1();
        double p1y = ((Line2D)shape).getY1();
        
        double px = p.getX();
        double py = p.getY();
        
        double minx = Math.min(p1x, px);
        double maxx = Math.max(p1x, px);
        double miny = Math.min(p1y, py);
        double maxy = Math.max(p1y, py);
        
        double distx = maxx - minx;
        double disty = maxy - miny;
        
        double distancia = Math.sqrt(Math.pow(distx, 2) + Math.pow(disty, 2));
        
        if(distancia <= 4)
        {
            cercano = true;
        }
        return(cercano);
    }    
    
    /**
     * Devuelve si un punto está contenido en otro
     * @param p punto  acomprobar si esta contenido
     * @return 
     */
    @Override
    public boolean contains(Point2D p)
    {
        return isNear(p);
    }
    
    /**
     * Devuelve si la figura es rellenable
     * @return En este caso es false
     */
    @Override
    public boolean esRellenable ()
    {
        return(false);
    }
    
    /**
     * Devuelve el MiRectangulo en el que está contenida la MiPunto
     * @return MiRectangulo
     */
    @Override
    public MiRectangulo getBounds()
    {
        // Crearemos un rectangulo en el el punto sera el centro
        int p1x = (int) (((Line2D)shape).getX1() -3);
        Point p = new Point ((int)((Line2D)shape).getX1()-4, (int)((Line2D)shape).getY1()+4);
        Point p2 = new Point ((int)((Line2D)shape).getX2()+4, (int)((Line2D)shape).getY2()-4);
        MiRectangulo frontera = new MiRectangulo (p, p2);
        frontera.setColorTrazo(Color.ORANGE);
        
        //Trazo
        Stroke trazo;
        float patronDiscontinuidad[] = {5.0f, 5.0f};
        trazo = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
        frontera.setStroke(trazo);
        
        return(frontera);
    }
    
    /**
     * Devuelve el punto superior izquierda del Bound
     * @return Point
     */
    @Override
    public Point getLocation ()
    {
        return (new Point((int)((Line2D) shape).getP1().getX(), (int)((Line2D) shape).getP1().getY()));
    }
    
    /**
     * Establece el nuevo punto en el que se apoya la figura.
     * Es el punto superior-izquierda del Bound
     * @param p 
     */
    @Override
    public void setLocation (Point2D p)
    {        
        shape = new Line2D.Double (p, p);
    }
}
