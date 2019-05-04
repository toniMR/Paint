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
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author antonio
 */
public class MiCurva extends MiForma{
    
    /**
     * Crea el objeto MiCurva
     * @param p1 punto inicial
     * @param control punto de control
     * @param p2 punto final
     */
    public MiCurva (Point2D p1, Point2D control, Point2D p2)
    {
        super.shape = new QuadCurve2D.Double (p1.getX(), p1.getY(), control.getX(), control.getY(), p2.getX(), p2.getY());
    }
    
    /**
     * Estable el punto de contro
     * @param control punto de control
     */
    public void setControlPoint(Point2D control)
    {
        QuadCurve2D.Double curvaAux = (QuadCurve2D.Double) super.shape;
        super.shape = new QuadCurve2D.Double (curvaAux.getX1(),curvaAux.getY1(), control.getX(), control.getY(), curvaAux.getX2(),curvaAux.getY2());
    }
    
    /**
     * Modifica el punto final
     * @param p2 punto final
     */
    public void setP2 (Point2D p2)
    {
        QuadCurve2D.Double curvaAux = (QuadCurve2D.Double) super.shape;
        super.shape = new QuadCurve2D.Double (curvaAux.getX1(),curvaAux.getY1(), curvaAux.getCtrlX(), curvaAux.getCtrlY(), p2.getX(), p2.getY());
    }

    /**
     * Devuelve si es rellenable
     * @return boolean
     */
    @Override
    public boolean esRellenable() {
        return(true);
    }

    /**
     * Devuelve el MiRectangulo en el que está contenida la MiCurva
     * @return MiRectangulo
     */
    @Override
    public MiRectangulo getBounds() {
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
        Point2D locOriginal = this.getLocation();
        
        Point2D primerPunto = ((QuadCurve2D)shape).getP1();
        Point2D ctrlPunto = ((QuadCurve2D)shape).getCtrlPt();
        Point2D segundoPunto = ((QuadCurve2D)shape).getP2();
        
        double cambioX = locOriginal.getX() - p.getX();
        double cambioY = locOriginal.getY() - p.getY();
        
        Point2D p1 = new Point2D.Double (primerPunto.getX()-cambioX , primerPunto.getY()-cambioY);
        Point2D cp = new Point2D.Double (ctrlPunto.getX()-cambioX , ctrlPunto.getY()-cambioY);
        Point2D p2 = new Point2D.Double (segundoPunto.getX()-cambioX , segundoPunto.getY()-cambioY);
        
        ((QuadCurve2D)shape).setCurve(p1, cp, p2);
        
    }
    
    
    
}
