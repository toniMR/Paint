/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;

/**
 *
 * @author antonio
 */
public class MiRectangulo extends MiForma
{
    /**
     * Crea el MiRectangulo
     * @param p punto pivote del MiRectangulo
     */
    public MiRectangulo (Point p)
    {
        shape = new Rectangle(p);
    }
    
    /**
     * Crea el MiRectangulo
     * @param p punto pivote del MiRectangulo
     * @param p2 punto inferior derecha del MiRectangulo
     */
    public MiRectangulo (Point p, Point p2)
    {
        shape = new Rectangle(p);
        ((Rectangle) shape).setFrameFromDiagonal(p, p2);
    }
    
    /**
     * Establece las coordenadas de la diagonal del MiRectangulo
     * @param p1 extremo de la diagonal
     * @param p2 extremo de la diagonal 
     */
    public void setFrameFromDiagonal(Point p1, Point p2)
    {
        ((Rectangle)shape).setFrameFromDiagonal(p1, p2);
    }
    
    /**
     * Devuelve si la figura es rellenable
     * @return En este caso devuelve true
     */
    @Override
    public boolean esRellenable ()
    {
        return(true);
    }
    
    /**
     * Devuelve el MiRectangulo en el que está contenido el MiRectangulo
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
        return (((Rectangle)shape).getLocation());
    }
    
    /**
     * Establece el nuevo punto en el que se apoya la figura.
     * Es el punto superior-izquierda del Bound
     * @param p 
     */
    @Override
    public void setLocation (Point2D p)
    {
        /*
        Point locOriginal = ((Rectangle)shape).getLocation();
        Point locNueva = new Point (locOriginal.x-((int)p.getX() - (int)p2.getX()), locOriginal.y-((int) p.getY() - (int) p2.getY()));
        */
        ((Rectangle)shape).setLocation((Point) p);
    }
    
    /**
     * Devuelve la anchura del MiRectangulo
     * @return double
     */
    public double getWidth()
    {
        return (((Rectangle)shape).getWidth());
    }
    
    /**
     * Devuelve la altura del MiRectangulo
     * @return double
     */
    public double getHeight()
    {
        return (((Rectangle)shape).getHeight());
    }
    /**
     * Devuelve el punto superior izquierda del MMiRectangulo
     * @return Point
     */
    public Point getP1()
    {
        return (((Rectangle)shape).getLocation());
    }
    
    /**
     * Devuelve el punto inferior derecha del MiRectangulo
     * @return 
     */
    public Point getP2()
    {
        return (new Point(getP1().x+(int)getWidth(), getP1().y-(int)getHeight()));
    }
}

