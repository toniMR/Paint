/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author antonio
 */
public class MiElipse extends MiForma{
    /**
     * Crea e objeto MiElipse
     * @param p 
     */
    public MiElipse (Point2D p)
    {
        shape = new Ellipse2D.Double(p.getX(), p.getY(), p.getX(), p.getY());
    }
    
    /**
     * Establece el punto superior izquierda y inferior derecha
     * @param p1 punto superior izquierda
     * @param p2 punto inferior derecha
     */
    public void setFrameFromDiagonal(Point2D p1, Point2D p2)
    {
        ((Ellipse2D)shape).setFrameFromDiagonal(p1, p2);
    }
    
    
    /**
     * Devuelve si es rellenable o no
     * @return boolean
     */
    public boolean esRellenable ()
    {
        return(true);
    }
    
    /**
     * Devuelve el MiRectangulo en el que está contenida la MiElipse
     * @return MiRectangulo
     */
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
        return (getBounds().getLocation());
    }
    
    /**
     * Establece el nuevo punto en el que se apoya la figura.
     * Es el punto superior-izquierda del Bound
     * @param p 
     */
    @Override
    public void setLocation (Point2D p)
    {       
        ((Ellipse2D)shape).setFrame(p, new Dimension ((int)((Ellipse2D)shape).getWidth(), (int)((Ellipse2D)shape).getHeight()));
    }

}
