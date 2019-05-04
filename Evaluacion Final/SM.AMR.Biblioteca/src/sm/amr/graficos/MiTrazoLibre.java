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
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 *
 * @author antonio
 */
public class MiTrazoLibre extends MiForma{
    /**
     * Crea un MiTrazoLibre
     */
    public MiTrazoLibre ()
    {
        shape = new GeneralPath();
    }
    
    /**
     * Establece el primer punto del MiTrazoLibre
     * @param p 
     */
    public void primerPunto(Point2D p)
    {
        ((GeneralPath) shape).moveTo(p.getX(), p.getY());
    }
    
    /**
     * Crea una linea desde el último punto añadido a este nuevo y lo añade
     * @param p nuevo punto
     */
    public void introducirPunto(Point2D p)
    {
        ((GeneralPath) shape).lineTo(p.getX(), p.getY());
    }
    
    /**Devuelve si es rellenable o no
     * @return En este caso es true
     */
    @Override
    public boolean esRellenable ()
    {
        return(true);
    }
    
    /**
     * Devuelve el MiRectangulo en el que está contenido el MiTrazoLibre
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
        return((((GeneralPath)shape).getBounds().getLocation()));
    }
    
    /**
     * Establece el nuevo punto en el que se apoya la figura.
     * Es el punto superior-izquierda del Bound
     * @param p 
     */
    @Override
    public void setLocation(Point2D p)
    {
        Point2D locOriginal = this.getLocation();
        double cambioX = locOriginal.getX() - p.getX();
        double cambioY = locOriginal.getY() - p.getY();
        
        AffineTransform t = new AffineTransform();
        t.translate(-cambioX, -cambioY);
        ((GeneralPath)shape).transform(t);
    }
}
