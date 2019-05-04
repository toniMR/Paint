/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author antonio
 */
public abstract class MiForma{

    Shape shape;
    
    private Color colorTrazo = Color.BLACK;
    private Color colorRelleno = Color.BLACK;
    private Color colorFrente = Color.RED;
    private Color colorFondo = Color.BLUE;
    
    private boolean relleno = false;
    private boolean degradado = false;
    private boolean horizontal = true;
    private boolean vertical = false;
    private boolean alisado = false;
    private boolean trazoPunteado = false;
    private float gradoTransparencia = 1.0f;
    private float grosor = 1;
    private boolean seleccionada = false;
    private Stroke stroke = null;
    
    /**
     * Pinta la figura shape de MiForma con todos sus atributos
     * @param g2d Graphics donde se pintará la figura
     */
    public void paint (Graphics2D g2d)
    {
        //Transparencia
        Composite comp = null;
        
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, gradoTransparencia);
        
        g2d.setComposite(comp);
        
        //Alisado
        RenderingHints render;
        if(!alisado )//Si no esta el alisado pongo antialiasoff
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
        else//Si estoy alisando pongo Antialiason
            render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(render);
        
        // Trazo
        // Si no se ha establecido un trazo se dibujara con el trazo por defecto
        if(stroke!=null)
        {
           g2d.setStroke(stroke); 
        }
        
        
        // Dibujar relleno si tiene
        if(relleno)
        {
            if(degradado)
            {
                Point pc1=null;
                Point pc2=null;
                MiRectangulo rec = this.getBounds();
                
                if(horizontal)
                {
                    pc1 = new Point(rec.getP1().x, rec.getP1().y-(int)(rec.getHeight()/2));
                    pc2 = new Point(rec.getP2().x, rec.getP1().y+(int)(rec.getHeight()/2));
                }
                else
                {
                    pc1 = new Point(rec.getP1().x+(int)(rec.getWidth()/2), rec.getP2().y);
                    pc2 = new Point(rec.getP1().x+(int)(rec.getWidth()/2), rec.getP1().y);
                }
                
                Paint d = new GradientPaint(pc1, this.colorFrente, pc2, this.colorFondo);
                g2d.setPaint(d);
                g2d.fill(shape);
            }
            else
            {
                g2d.setColor(colorRelleno);
                g2d.fill(shape);
            }
        }
        
        // Dibujar trazo
        g2d.setColor(colorTrazo);
        g2d.draw(shape); 
        
        if(seleccionada)
        {
            MiRectangulo frontera = this.getBounds();
            frontera.paint(g2d);
        }        
    }
    
    /**
     * Establece el color de Relleno
     * @param c Color que se establece
     */
    public void setColorRelleno (Color c)
    {
        this.colorRelleno = c;
    }
    
    /**
     * Establece el color de Trazo
     * @param c Color que se establece
     */
    public void setColorTrazo (Color c)
    {
        this.colorTrazo = c;
    }
    
    public abstract boolean esRellenable ();
    public abstract MiRectangulo getBounds ();
    public abstract void setLocation (Point2D p);
    public abstract Point getLocation ();
    
    /**
     * Establece si el trazo será punteado
     * @param trazoPunteado 
     */
    public void setTrazoPunteado(boolean trazoPunteado)
    {
        this.trazoPunteado = trazoPunteado;
    }
    
    /**
     * Establece si la figura estará rellenada
     * @param relleno 
     */
    public void setRelleno (boolean relleno)
    {
        this.relleno = relleno;
    }
    
    /**
     * Establece si la figura tendrá degradado
     * @param degradado 
     */
    public void setDegradado (boolean degradado)
    {
        this.degradado = degradado;
    }
    
    /**
     * Establece el color de frente del degradado
     * @param c Color establecido
     */
    public void setColorFrente (Color c)
    {
        this.colorFrente = c;
    }
    
    /**
     * Establece el color de fondo del degradado
     * @param c 
     */
    public void setColorFondo (Color c)
    {
        this.colorFondo = c;
    }
    
    /**
     * Establece si el degradado es horizontal
     * @param horizontal 
     */
    public void setHorizontal (boolean horizontal)
    {
        if(horizontal)
        {
            this.horizontal = horizontal;
            this.vertical = false;
        }
        else
        {
            this.horizontal = false;
            this.vertical = true;
        }
    }
    
    /**
     * Establece si el degradado es vertical
     * @param vertical 
     */
    public void setVertical (boolean vertical)
    {
        if(vertical)
        {
            this.vertical = vertical;
            this.horizontal = false;
        }
        else
        {
            this.vertical = false;
            this.horizontal = true;
        }
    }
    
    /**
     * Establece el grado de transparencia de las figuras
     * @param gradoTransparencia 
     */
    public void setGradoTransparencia (float gradoTransparencia)
    {
        this.gradoTransparencia = gradoTransparencia;
    }
    
    /**
     * Establece si la figura estará alisada
     * @param alisado 
     */
    public void setAlisado (boolean alisado)
    {
        this.alisado = alisado;
    }
    
    /**
     * Establece el stroke de la figura
     * @param stroke 
     */
    public void setStroke (Stroke stroke)
    {
        this.stroke = stroke;
    }
    
    /**
     * Establece el grosor del trazo
     * @param grosor 
     */
    public void setGrosorTrazo (float grosor)
    {
        if(stroke!=null)
        {
            BasicStroke strokeAux = (BasicStroke)stroke;
            stroke = new BasicStroke(grosor, strokeAux.getEndCap(), strokeAux.getLineJoin(), strokeAux.getMiterLimit(), strokeAux.getDashArray(),strokeAux.getDashPhase());
            this.grosor = grosor;
        }
        else
        {
            stroke = new BasicStroke (grosor);
        }
        
    }
    
    /**
     * Establece si la figura está seleccionada. Para saber si pintar su
     * frontera o no
     * @param seleccionada 
     */
    public void setSeleccionada (boolean seleccionada)
    {
        this.seleccionada = seleccionada;
    }
    
    /**
     * Devuelve si la figura está seleccionada
     * @return 
     */
    public boolean getSeleccionada ()
    {
        return(this.seleccionada);
    }
    
    /**
     * devuelve si la figura está rellena
     * @return 
     */
    public boolean getRelleno()
    {
        return (this.relleno);
    }
    
    /**
     * Devuelve si la figura está alisada
     * @return 
     */
    public boolean getAlisado()
    {
        return (this.alisado);
    }
    
    /**
     * Devuelve el grado de transparencia de la figura
     * @return 
     */
    public float getGradoTransparencia()
    {
        return (this.gradoTransparencia);
    }
    
    /**
     * Devuelve si el trazo de la figura es punteado
     * @return 
     */
    public boolean getPunteada()
    {
        return (this.trazoPunteado);
    }
    
    /**
     * Devuelve el grosor del trazo
     * @return 
     */
    public int getGrosorTrazo()
    {
        return(int) (this.grosor);
    }
    
    /**
     * Devuelve si la figura tiene degradado
     * @return 
     */
    public boolean getDegradado()
    {
        return (this.degradado);
    }
    
    /**
     * Devuelve el color del trazo
     * @return 
     */
    public Color getColorTrazo()
    {
        return(this.colorTrazo);
    }
    
    /**
     * Devuelve el color del relleno
     * @return 
     */
    public Color getColorRelleno()
    {
        return(this.colorRelleno);
    }
    
    /**
     * Devuelve el color de fondo del degradado
     * @return 
     */
    public Color getColorFondo()
    {
        return(this.colorFondo);
    }
    
    /**
     * Devuelve el color de frente del degradado
     * @return 
     */
    public Color getColorFrente()
    {
        return(this.colorFrente);
    }
    
    /**
     * Devuelve si el degradado de la figura es vertical
     * @return 
     */
    public boolean getVertical()
    {
        return(this.vertical);
    }
    
    /**
     * Devuelve si el degradado de la figura es vertical
     * @return 
     */
    public boolean getHorizontal()
    {
        return(this.horizontal);
    }
    
    /**
     * Devuelve si el punto está contenido en la figura
     * @param p Punto a comprobar si está contenido
     * @return 
     */
    public boolean contains (Point2D p)
    {
        return(shape.contains(p));
    }
    
}
