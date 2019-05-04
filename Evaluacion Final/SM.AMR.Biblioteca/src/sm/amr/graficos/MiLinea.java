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
import java.awt.geom.Rectangle2D;

/**
 *
 * @author antonio
 */
public class MiLinea extends MiForma
{
    /**
     * Crea el objeto MiLinea
     */
    public MiLinea ()
    {
    }
    
    /**
     * Crea el objeto MiLinea
     * @param p1 Extremo de la MiLinea
     * @param p2 Extremo de la MiLinea
     */
    public MiLinea (Point2D p1, Point2D p2)
    {
        shape = new Line2D.Double (p1, p2);
    }
    
    /**
     * Comprueba si el punto está cerca de la figura
     * @param p punto a comprobar si está cerca
     * @return 
     */
    public boolean isNear(Point2D p)
    {
        return (((Line2D) shape).ptLineDist(p)<=2.0);
    }    
    
    /**
     * Comprueba si el punto está contenido en la figura
     * @param p
     * @return 
     */
    @Override
    public boolean contains(Point2D p)
    {
        return isNear(p);
    }
    
    /**
     * Devuelve el primer extremo de la MiLinea
     * @return 
     */
    public Point2D getP1()
    {
        return ((Line2D) shape).getP1();
    }
    
    /**
     * Devuelve el segundo extremo de la MiLinea
     * @return 
     */
    public Point2D getP2()
    {
        return ((Line2D) shape).getP2();
    }
    
    /**
     * Establece los 2 nuevos extremos de la MiLinea
     * @param p1
     * @param p2 
     */
    public void setLine(Point2D p1, Point2D p2)
    {
        ((Line2D) shape).setLine(p1, p2);
    }
    
    /**
     * Devuelve si es rellenable o no. En este objeto devolvera false
     * @return 
     */
    @Override
    public boolean esRellenable ()
    {
        return(false);
    }
    
    /**
     * Devuelve el MiRectangulo en el que está contenida la MiLinea
     * @return MiRectangulo
     */
    @Override
    public MiRectangulo getBounds()
    {
        Point p = new Point (super.shape.getBounds().x, super.shape.getBounds().y);
        Point p2 = new Point (p.x + super.shape.getBounds().width, p.y + super.shape.getBounds().height);
        MiRectangulo frontera = new MiRectangulo (p, p2);
        frontera.setColorTrazo(Color.ORANGE);
        
        //Trazo
        Stroke trazo;
        float patronDiscontinuidad[] = {15.0f, 15.0f};
        trazo = new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
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
        return(getBounds().getLocation());
    }
    
    /**
     * Establece el nuevo punto en el que se apoya la figura.
     * Es el punto superior-izquierda del Bound
     * @param p 
     */
    @Override
    public void setLocation(Point2D p)
    {
        Point locOriginal = getLocation();
        
        Point2D p1 = this.getP1();
        Point2D p2 = this.getP2();
        
        double cambioX = locOriginal.getX() - p.getX();
        double cambioY = locOriginal.getY() - p.getY();
        
        Point2D p1cambiado = new Point2D.Double(p1.getX()-cambioX, p1.getY()-cambioY);
        Point2D p2cambiado = new Point2D.Double(p2.getX()-cambioX, p2.getY()-cambioY);
        
        ((Line2D) shape).setLine(p1cambiado, p2cambiado);
    }
}
