/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author antonio
 */
public class MiPersonalizada extends MiForma{
    private Ellipse2D.Double circle = new Ellipse2D.Double();
    private Line2D.Double line = new Line2D.Double();
    
    private Area circ;
    private Area ov;
    private Area leaf1;
    private Area leaf2;
    private Area st1;
    private Area st2;
    
    private Area cabeza, ojo1, ojo2, oreja1, oreja2, ocico, lengua, bigote1,
            bigote2, bigote3, bigote4, completa;
    
    private Point2D p;
    
    /**
     * Crea la figura MiPersonalizada 
     * @param p punto pivote de la figura
     */
    public MiPersonalizada(Point2D p)
    {
        formarFigura(p);
    }
    
    /**
     * Establece las partes de la figura 
     * @param p punto pivote de la figura
     */
    public void formarFigura(Point2D p)
    {
        this.p = p;
        
        double ew = p.getX();
        double eh = p.getY();
        
        // Crear la cabeza
        circle.setFrame(ew-40, eh-25, 80.0, 50.0);
        cabeza = new Area(circle);
        
        circle.setFrame(ew-20, eh-5, 10.0, 10.0);
        ojo1 = new Area(circle);
        
        circle.setFrame(ew+10, eh-5, 10.0, 10.0);
        ojo2 = new Area(circle);
        
        circle.setFrame(ew-35, eh-30, 20.0, 20.0);
        oreja1 = new Area(circle);
        
        circle.setFrame(ew+15, eh-30, 20.0, 20.0);
        oreja2 = new Area(circle);
        
        
        
        cabeza.subtract(ojo1);
        cabeza.subtract(ojo2);
        cabeza.add(oreja1);
        cabeza.add(oreja2);
        //g2.fill(cabeza);
        
        
        // Crear ocico
        circle.setFrame(ew-20, eh+10, 40.0, 30.0);
        ocico = new Area(circle);
        
        circle.setFrame(ew-40, eh+22, 40, 2.0);
        bigote1 = new Area (circle);
        circle.setFrame(ew-40, eh+25, 40, 2.0);
        bigote2 = new Area (circle);
        circle.setFrame(ew, eh+22, 40, 2.0);
        bigote3 = new Area (circle);
        circle.setFrame(ew, eh+25, 40, 2.0);
        bigote4 = new Area (circle);
        
        ocico.add(bigote1);
        ocico.add(bigote2);
        ocico.add(bigote3);
        ocico.add(bigote4);
        //g2.fill(ocico);
        
        
        // Crear lengua
        circle.setFrame(ew-5, eh+25, 10.0, 25.0);
        lengua = new Area(circle);
        //g2.fill(lengua);
        
        completa = cabeza;
        completa.add(ocico);
        completa.add(lengua);
        super.shape = completa;
    }

    /**
     * Devuelve si es rellenable
     * @return En este caso es true
     */
    @Override
    public boolean esRellenable() {
        return(true);
    }

    /**
     * Devuelve el MiRectangulo en el que está contenida la MiPersonalizada
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
     * Establece el nuevo punto en el que se apoya la figura.
     * Es el punto superior-izquierda del Bound
     * @param p 
     */
    @Override
    public void setLocation(Point2D p) {
        formarFigura(p);
        /*
        Point locOriginal = getLocation();
        Point locNueva = new Point (locOriginal.x-((int)p.getX() - (int)p2.getX()), locOriginal.y-((int) p.getY() - (int) p2.getY()));
        
        formarFigura(locNueva);
        */
    }

    /**
     * Devuelve el punto superior izquierda del Bound
     * @return Point
     */
    @Override
    public Point getLocation() {
        return(new Point((int)this.p.getX(), (int)this.p.getY()));
    }
}
