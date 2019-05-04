/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.amr.IU;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import sm.amr.graficos.MiCurva;
import sm.amr.graficos.MiElipse;
import sm.amr.graficos.MiForma;
import sm.amr.graficos.MiLinea;
import sm.amr.graficos.MiPersonalizada;
import sm.amr.graficos.MiPunto;
import sm.amr.graficos.MiRectangulo;
import sm.amr.graficos.MiTrazoLibre;



/**
 *
 * @author antonio
 */


public class Lienzo extends javax.swing.JPanel {

    private Point2D p = null;
    private Point2D paux = null;
    // Estos puntos los necesitamos para mover el punto correctamente
    private boolean mover = true;
    private Point2D pauxMover = null;
    private Point2D pauxMover2 = null;
    
    private EnumObjeto modo = EnumObjeto.PUNTO;
    private boolean trazoPunteado = false;
    private boolean relleno = false;
    private boolean degradado = false;
    private boolean horizontal = true;
    private boolean vertical = false;
    private boolean alisado = false;
    private boolean controlCurvaEstablecido = false;
    private boolean curvaTerminada = false;
    private float gradoTransparencia = 1.0f;
    private float grosorTrazo = 1;
    private boolean seleccionar = false;
    private ArrayList<MiForma> misFormas = new ArrayList();
    MiForma miforma = null;
    
    private MiForma seleccionada = null;
    
    private Color colorRelleno = Color.black;
    private Color colorTrazo = Color.black;
    
    private Color colorFrente = Color.RED; 
    private Color colorFondo = Color.BLUE; 
    
    // Botones de la ventana para poder modificarlos con los atributos de la figura seleccionada
    // (Los inicializo a null por si a alguien no le hace falta que se vayan actualizando
    // no de error y no actualizará el estado de los botones en la ventana)
    private JToggleButton puntoBoton = null, lineaBoton = null, elipseBoton = null,
            rectanguloBoton = null, curvaBoton = null, trazoLibreBoton = null,
            personalizadaBoton = null, rellenoBoton = null, alisadoBoton = null, 
            punteadoBoton = null, seleccionarBoton=null;
    
    private JCheckBox degradadoCheckbox = null, verticalCheckbox = null, horizontalCheckbox = null;
    
    private JComboBox paletaTrazo=null, paletaRelleno=null, paletaFrente=null, paletaFondo=null, grosorCombobox=null;
    
    private JSlider gradoTransparenciaSlider=null;
    
    // Meter las paletas de colores
    
    
    // Figuras --------------------------------------------
    
    /*
    //Quadratic Curve Segment
    private QuadCurve2D qcs = new QuadCurve2D.Double();
    //Cubic Curve Segment
    private CubicCurve2D ccs = new CubicCurve2D.Double();
    //Arc
    private Arc2D arc = new Arc2D.Double();
    */
    // ----------------------------------------------------
    
    /**
     * Constructor del lienzo. Inicia los componentes que contiene el frame
     */
    public Lienzo() {
        initComponents();
    }    
    
    /**
     * Devuelve el modo de figura que pintara la proxima vez
     * @return EnumObjeto
     */
    public EnumObjeto getModo()
    {
        return (this.modo);
    }
    
    /**
     * Devuelve si la siguiente figura a pintar será rellenada
     * @return boolean
     */
    public boolean getRelleno()
    {
        return (this.relleno);
    }
    
    /**
     * Devuelve si la opcion de seleccionar está activada
     * @return boolean
     */
    public boolean getSeleccionar()
    {
        return(this.seleccionar);
    }
    
    /**
     * Devuelve si la siguiente figura a pinta será alisada
     * @return boolean
     */
    public boolean getAlisado()
    {
        return(this.alisado);
    }
    
    /**
     * Devuelve el grado de Transparencia de la siguiente figura a pintar
     * @return float
     */
    public float getGrosorTrazo()
    {
        return(this.grosorTrazo);
    }
        
    /**
     * Devuelve la MiForma seleccionada
     * @return MiForma
     */
    public MiForma getSeleccionada()
    {
        return(seleccionada);
    }
    
    /**
     * Devuelve si la siguiente figura tendrá degadado. Para que salga degradada
     * la variable relleno tiene que estar a true
     * @return boolean
     */
    public boolean getDegradado()
    {
        return(this.degradado);
    }
    
    /**
     * Indica el color del relleno de la siguiente figura a pintar
     * @param c Color que se establece
     */
    public void setColorRelleno(Color c)
    {
        this.colorRelleno = c;
    }
    
    /**
     * Indica el color del trazo de la siguiente figura a pintar
     * @param c Color que se establece
     */
    public void setColorTrazo(Color c)
    {
        this.colorTrazo = c;
    }
    
    /**
     * Indica el color de frente del degradado de la siguiente figura a pintar
     * @param c Color que se establece
     */
    public void setColorFrente(Color c)
    {
        this.colorFrente = c;
    }
    
    /**
     * Indica el color de fondo del degradado de la siguiente figura a pintar
     * @param c Color que se establece
     */
    public void setColorFondo(Color c)
    {
        this.colorFondo = c;
    }
    
    /**
     * Indica el modo de figura que será pintada en la siguiente vez
     * @param m Modo que se establece
     */
    public void setModo(EnumObjeto m)
    {
        this.modo = m;
    }
    
    /**
     * La siguiente figura tendrá el trazo discontinuo si se le pasa true
     * @param trazoPunteado boolean
     */
    public void setTrazoPunteado(boolean trazoPunteado)
    {
        this.trazoPunteado = trazoPunteado;
    }
    
    /**
     * La siguiente figura tendrá relleno si se le pasa true. Esta variable
     * tiene que estar a true si se quiere dibujar con degradado
     * @param r boolean
     */
    public void setRelleno(boolean r)
    {
        this.relleno = r;
    }
    
    /**
     * La siguiente figura tendrá degradado si se le pasa a true. La variable 
     * relleno debe estar a true también para que salga.
     * @param degradado boolean
     */
    public void setDegradado (boolean degradado)
    {
        this.degradado = degradado;
    }
    
    /**
     * Si se le pasa true el degradado será horizontal. Relleno y degradado
     * deben estar a true
     * @param horizontal boolean
     */
    public void setHorizontal(boolean horizontal)
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
     * Si se le pasa true el degradado será vertical. Relleno y degradado
     * deben estar a true
     * @param vertical boolean
     */
    public void setVertical(boolean vertical)
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
     * Cambia el modo a seleccionar si se le pasa true. Se podrán mover figuras
     * y modificarlas mientras estén seleccionadas
     * @param seleccionar boolean
     */
    public void setSeleccionar(boolean seleccionar)
    {
        // Si se pone a falso habra que poner todo a null y quitar los cuadros de Seleccion de misFormas)
        this.seleccionar = seleccionar;
        if(!seleccionar)
        {
            if(this.seleccionada!=null)
            {
                this.seleccionada.setSeleccionada(false);
            }
            seleccionada = null;
            this.setModificadoresVentana();
        }
        this.repaint();
    }
    
    /**
     * La siguiente figura estará alisada si se le pasa true
     * @param alisado boolean
     */
    public void setAlisado(boolean alisado)
    {
        this.alisado = alisado;
    }
    
    /**
     * Indica el grado de transparencia de la siguiente figura
     * @param transparencia valor entre 0.0f y 1.0f
     */
    public void setGradoTransparencia(float transparencia)
    {
        this.gradoTransparencia = transparencia;
    }
    
    /**
     * Indica el grosor de trazo de la siguiente figura a pintar
     * @param grosorTrazo valor del grosor de trazo
     */
    public void setGrosorTrazo(float grosorTrazo)
    {
        this.grosorTrazo = grosorTrazo;
    }
    
    /**
     * Moverá la figura seleccionada al fondo.
     */
    public void alFondo()
    {
        if(seleccionada != null)
        {
            int index = this.misFormas.indexOf(seleccionada);
            this.misFormas.add(0, seleccionada);
            this.misFormas.remove(index+1);
            //seleccionada=misFormas.get(0);
            repaint();
        }
    }
    
    /**
     * Moverá la figura seleccionada al frente.
     */
    public void alFrente()
    {
        if(seleccionada != null)
        {
            int index = this.misFormas.indexOf(seleccionada);
            this.misFormas.add(seleccionada);
            this.misFormas.remove(index);
            //seleccionada=misFormas.get(index);
            repaint();
        }
    }
    
    /**
     * Pinta todas las figuras creadas e introducidas en el vector misFormas
     * @param g Graphics sobre el cual se pinta
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        for(MiForma f:misFormas)
        {
            f.paint(g2d);
        }
    }
    
    /**
     * Estable los atributos seleccionados a la figura que se ha creado
     */
    private void setAtributos()
    {
        // Atributos de la FIGURA 
        miforma.setColorRelleno(colorRelleno);
        miforma.setColorTrazo(colorTrazo);
        miforma.setColorFondo(colorFondo);
        miforma.setColorFrente(colorFrente);
        
        if(this.relleno==true)
        {
            if(miforma.esRellenable())
            {
                miforma.setRelleno(true);
                
                if(this.degradado)
                {
                    miforma.setDegradado(true);
                }
                else
                {
                    miforma.setDegradado(false);
                }
            }
            else
            {
                miforma.setRelleno(false);
            }
        }
        else
        {
            miforma.setRelleno(false);
        }
        
        if(this.vertical)
        {
            miforma.setVertical(true);
            miforma.setHorizontal(false);
        }
        else
        {
            miforma.setVertical(false);
            miforma.setHorizontal(true);
        }
        miforma.setGrosorTrazo(this.grosorTrazo);
        miforma.setGradoTransparencia(gradoTransparencia);
        miforma.setAlisado(this.alisado);
        if(trazoPunteado)
        {
            miforma.setTrazoPunteado(true);
            float patronDiscontinuidad[] = {12.0f, 12.0f};
            miforma.setStroke(new BasicStroke((float)this.grosorTrazo, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f));
        }
        else
        {
            miforma.setTrazoPunteado(false);
        }
    }
    
    
    /**
     * Devuelve la primera figura que contenga el punto p. Será la que esté 
     * más al frente.
     * @param p El punto que debe contener la figura para que sea devuelta
     * @return MiForma
     */
    private MiForma getSelectedShape(Point2D p)
    {
        boolean encontrado = false;
        MiForma s = null;
        
        for(int i=this.misFormas.size()-1; i>=0 && !encontrado; i--)
        {
            if(misFormas.get(i).contains(p))
            {
                s = misFormas.get(i);
                encontrado = true;
            }
        }
        return (s);
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Punto.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setpuntoBoton(JToggleButton boton)
    {
        this.puntoBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Linea.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setLineaBoton(JToggleButton boton)
    {
        this.lineaBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Elipse.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setElipseBoton(JToggleButton boton)
    {
        this.elipseBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Rectangulo.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setRectanguloBoton (JToggleButton boton)
    {
        this.rectanguloBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Curva.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setCurvaBoton(JToggleButton boton)
    {
        this.curvaBoton= boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo TrazoLibre.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setTrazoLibreBoton(JToggleButton boton)
    {
        this.trazoLibreBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Personalizada.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setPersonalizadaBoton(JToggleButton boton)
    {
        this.personalizadaBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Relleno.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setRellenoBoton(JToggleButton boton)
    {
        this.rellenoBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Alisado.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setAlisadoBoton(JToggleButton boton)
    {
        this.alisadoBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que cambie el lienzo a modo Seleccionar.
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setSeleccionarBoton (JToggleButton boton)
    {
        this.seleccionarBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que el lienzo pinte con transparencia
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param slider JSlider
     */
    public void setGradoTransparenciaSlider(JSlider slider)
    {
        this.gradoTransparenciaSlider = slider;
    }
    
    /**
     * Establece el boton que se pulsa para que el lienzo pinte con trazo punteado
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param boton JToggleButton
     */
    public void setPunteadaBoton(JToggleButton boton)
    {
        this.punteadoBoton = boton;
    }
    
    /**
     * Establece el boton que se pulsa para que el lienzo pinte con degradado
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param checkbox JCheckBox
     */
    public void setDegradadoCheckbox(JCheckBox checkbox)
    {
        this.degradadoCheckbox = checkbox;
    }
    
    /**
     * Establece el boton que se pulsa para el lienzo pinte con degradado vertical
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param checkbox JCheckBox
     */
    public void setVerticalCheckbox(JCheckBox checkbox)
    {
        this.verticalCheckbox = checkbox;
    }
    
    /**
     * Establece el boton que se pulsa para el lienzo pinte con degradado horizontal
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param checkbox JCheckBox
     */
    public void setHorizontalCheckbox(JCheckBox checkbox)
    {
        this.horizontalCheckbox = checkbox;
    }
    
    /**
     * Establece el boton que se pulsa para cambiar el color del trazo de las figuras
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param paleta JComboBox 
     */
    public void setPaletaTrazo(JComboBox paleta)
    {
        this.paletaTrazo = paleta;
    }
    
    /**
     * Establece el boton que se pulsa para cambiar el color del relleno de las figuras
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param paleta JComboBox
     */
    public void setPaletaRelleno(JComboBox paleta)
    {
        this.paletaRelleno = paleta;
    }
    
    /**
     * Establece el boton que se pulsa para cambiar el color del frente de degradado de las figuras
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param paleta JComboBox
     */
    public void setPaletaFrente(JComboBox paleta)
    {
        this.paletaFrente = paleta;
    }
    
    /**
     * Establece el boton que se pulsa para cambiar el color del fondo de degradado de las figuras
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param paleta JComboBox
     */
    public void setPaletaFondo(JComboBox paleta)
    {
        this.paletaFondo = paleta;
    }
    
    /**
     * Establece el boton que se pulsa para cambiar el grosor del trazo de las figuras
     * Otros métodos lo usan para refrescar los botones al cambiar de lienzo o figura seleccionada.
     * @param combobox JComboBox
     */
    public void setGrosorCombobox (JComboBox combobox)
    {
        this.grosorCombobox = combobox;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(254, 254, 254));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Establece los pasos cuando se detecta un evento Click
     * @param evt eventoClick
     */
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        paux = evt.getPoint();
        
        if (this.seleccionar)
        {
            // Si ya hay alguna seleccionada
            if(seleccionada != null)
            {
                MiForma formaAux = getSelectedShape(p);
                
                if(formaAux != null)
                {
                    // Si la que he seleccionado ya estaba seleccionada
                    if(formaAux.getSeleccionada())
                    {
                        seleccionada.setSeleccionada(false);
                        seleccionada = null;
                    }
                    // Si la que he seleccionado no estaba seleccionada
                    else
                    {
                        seleccionada.setSeleccionada(false);

                        seleccionada = formaAux;
                        seleccionada.setSeleccionada(true);
                    }
                }
            }
            else
            {
                seleccionada = getSelectedShape(p);
                if(seleccionada != null)
                {
                    seleccionada.setSeleccionada(true);
                }
            }
        }
        else
        {
            switch(modo)
            {
                case PUNTO:
                {
                    miforma = new MiPunto (p);
                    this.setAtributos();
                    misFormas.add(miforma);
                    break;
                }
                case QUADCURVESEGMENT:
                {
                    // Reiniciamos la variable con la que vemos si se estableció el punto de control
                    this.controlCurvaEstablecido=false;
                    this.curvaTerminada=true;
                    break;
                }
                case MIPERSONALIZADA:
                {
                    miforma = new MiPersonalizada(p);
                    this.setAtributos();
                    misFormas.add(miforma);
                    break;
                }
                default:
                {
                    // Si hacemos click y no es ningun caso anterior borramos la figura creada en pressed
                    this.misFormas.remove(miforma);
                    break;
                }
            }
        }
        this.setModificadoresVentana();
        this.repaint();
    }//GEN-LAST:event_formMouseClicked

    /**
     * Establece los pasos cuando se detecta un evento Dragged
     * @param evt eventoDragged
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        paux = evt.getPoint();
        
        
        if(seleccionar)
        {
            if(seleccionada != null)
            {
                if(mover)
                {
                    // Cambio entre pauxMover y paux
                    int cambioX = (int)(pauxMover.getX() - paux.getX());
                    int cambioY = (int)(pauxMover.getY() - paux.getY());

                    // Cambiar el getLocation por ese cambio calculado
                    Point locOriginal = seleccionada.getLocation();
                    Point locNueva = new Point (locOriginal.x-cambioX, locOriginal.y-cambioY);

                    // Hacer el setLocation a ese punto cambiado
                    seleccionada.setLocation(locNueva);
                    
                    // Actualizar el punto referencia para calcular los cambios
                    pauxMover = paux;
                }
            }
        }
        else
        {
            switch(modo)
            {
                case LINEA:
                {
                    ((MiLinea)miforma).setLine(((MiLinea)miforma).getP1(), paux);
                    break;
                }
                case RECTANGULO:
                {
                    ((MiRectangulo)miforma).setFrameFromDiagonal((Point)p, (Point)paux);
                    break;
                }
                case CIRCULO:
                {
                    ((MiElipse)miforma).setFrameFromDiagonal((Point)p, (Point)paux);
                    break;
                }
                case QUADCURVESEGMENT:
                {
                    if(!controlCurvaEstablecido)
                    {
                        ((MiCurva)miforma).setControlPoint(paux);
                    }
                    break;
                }
                case GENERALPATH:
                {
                    ((MiTrazoLibre)miforma).introducirPunto(paux);
                    break;
                }
            }
        }
        
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * Establece los pasos cuando se detecta un evento Pressed
     * @param evt EventoPressed
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        p = evt.getPoint();
        if(seleccionar)
        {
            if(seleccionada!=null)
            {
                // HACER Comprobar si se ha preseionado dentro de la figura para moverla
                if(seleccionada.contains(p))
                {
                    this.mover = true;
                    pauxMover=p;
                }
            }
        }
        else
        {
            switch(modo)
            {
                case LINEA:
                {
                    miforma = new MiLinea(p,p);
                    this.setAtributos();
                    misFormas.add(miforma);
                    break;
                }
                case RECTANGULO:
                {
                    miforma = new MiRectangulo((Point) p);
                    this.setAtributos();
                    misFormas.add(miforma);
                    break;
                }
                case CIRCULO:
                {
                    miforma = new MiElipse(p);
                    this.setAtributos();
                    misFormas.add(miforma);
                    break;
                }
                case QUADCURVESEGMENT:
                {
                    if(!controlCurvaEstablecido)
                    {
                        miforma = new MiCurva(p, p, p);
                        this.setAtributos();
                        misFormas.add(miforma);
                        
                        // Para que funcione el evento moved despues de haber creado ya una hay que ponerlo en false
                        this.curvaTerminada=false;
                        break;
                    }
                }
                case GENERALPATH:
                {
                    miforma = new MiTrazoLibre();
                    ((MiTrazoLibre)miforma).primerPunto(p);
                    this.setAtributos();
                    misFormas.add(miforma);
                }
            }
        }
    
    }//GEN-LAST:event_formMousePressed

    /**
     * Establece los pasos cuando se detecta un evento Moved
     * @param evt EventoMoved
     */
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        if(modo==EnumObjeto.QUADCURVESEGMENT)
        {
            if(!seleccionar)
            {
                // Si curvaTerminada es true y entrase modificaria la ultima curva creada al desactivar el modo de seleccion
                if(!this.curvaTerminada)
                {
                    if(this.controlCurvaEstablecido)
                    {
                        paux = evt.getPoint();
                        ((MiCurva)miforma).setP2(paux);
                        this.repaint();
                    }
                }
            }
        }
    }//GEN-LAST:event_formMouseMoved

    /**
     * Establece los pasos cuando se detecta un evento Released
     * @param evt EventoReleased
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if(modo==EnumObjeto.QUADCURVESEGMENT)
        {
            // Hay que comprobar si no se ha terminado la curva porque si no 
            // no entra en la condicion del click para la curva y no se terminara de crear la curva
            if(!controlCurvaEstablecido)
            {
                this.controlCurvaEstablecido=true;
            }
        }
        
        this.mover=false;
    }//GEN-LAST:event_formMouseReleased
    
    /**
     * Modifica el estado (Pulsado/No pulsado) de los botones de la Ventana dependiendo de 
     * la figura seleccionada o lienzo seleccionado. Para ello se tienen que establecer 
     * los botones. Si no se establecen no se refrescaran los estados de estos
     */
    public void setModificadoresVentana()
    {
        if(seleccionada!=null)
        {
            // Cambiar el checbox de relleno de la Ventana segun si seleccionada tiene relleno o no
            if(rellenoBoton!=null)
            {
                if(seleccionada.getRelleno())
                {
                    this.rellenoBoton.setSelected(true);
                }
                else
                {
                    this.rellenoBoton.setSelected(false);
                }
            }

            if(alisadoBoton!=null)
            {
                if(seleccionada.getAlisado())
                {
                    this.alisadoBoton.setSelected(true);
                }
                else
                {
                    this.alisadoBoton.setSelected(false);
                }
            }
            
            
            if(gradoTransparenciaSlider!=null)
            {
                this.gradoTransparenciaSlider.setValue((int)(seleccionada.getGradoTransparencia()*100));
            }
            

            if(punteadoBoton!=null)
            {
                if(seleccionada.getPunteada())
                {
                    this.punteadoBoton.setSelected(true);
                }
                else
                {
                    this.punteadoBoton.setSelected(false);
                }
            }

            if(degradadoCheckbox!=null)
            {
                if(seleccionada.getDegradado())
                {
                    this.degradadoCheckbox.setSelected(true);
                }
                else
                {
                    this.degradadoCheckbox.setSelected(false);
                }
            }

            if(paletaTrazo!=null)
            {
                paletaTrazo.setBackground(seleccionada.getColorTrazo());
            }

            if(paletaRelleno!=null)
            {
                paletaRelleno.setBackground(seleccionada.getColorRelleno());
            }

            if(paletaFondo!=null)
            {
                paletaFondo.setBackground(seleccionada.getColorFondo());
            }

            if(paletaFrente!=null)
            {
                paletaFrente.setBackground(seleccionada.getColorFrente());
            }
            
            if(grosorCombobox!=null)
            {
                grosorCombobox.setSelectedIndex(seleccionada.getGrosorTrazo()-1);
            }

            if(verticalCheckbox!=null)
            {
                verticalCheckbox.setSelected(seleccionada.getVertical());
            }

            if(horizontalCheckbox!=null)
            {
                horizontalCheckbox.setSelected(seleccionada.getHorizontal());
            }
        }
        else
        {
            switch(modo)
            {
                case PUNTO:
                    if(this.puntoBoton!=null)
                    {
                        this.puntoBoton.doClick();
                    }
                    break;                    
                case LINEA:
                    if(this.lineaBoton!=null)
                    {
                        this.lineaBoton.doClick();
                    }
                    break;
                case CIRCULO:
                    if(this.elipseBoton!=null)
                    {
                        this.elipseBoton.doClick();
                    }
                    break;
                case RECTANGULO:
                    if(this.rectanguloBoton!=null)
                    {
                        this.rectanguloBoton.doClick();
                    }
                    break;
                case QUADCURVESEGMENT:
                    if(this.curvaBoton!=null)
                    {
                        this.curvaBoton.doClick();
                    }
                    break;
                case GENERALPATH:
                    if(this.trazoLibreBoton!=null)
                    {
                        this.trazoLibreBoton.doClick();
                    }
                    break;
                case MIPERSONALIZADA:
                    if(this.personalizadaBoton!=null)
                    {
                        this.personalizadaBoton.doClick();
                    }
                    break;
            }
            
            if(rellenoBoton!=null)
            {
                if(this.relleno)
                {
                    this.rellenoBoton.setSelected(true);
                }
                else
                {
                    this.rellenoBoton.setSelected(false);
                }
            }

            if(alisadoBoton!=null)
            {
                if(alisado)
                {
                    this.alisadoBoton.setSelected(true);
                }
                else
                {
                    this.alisadoBoton.setSelected(false);
                }
            }
            
            if(gradoTransparenciaSlider!=null)
            {
                this.gradoTransparenciaSlider.setValue((int)(this.gradoTransparencia*100));
            }
            
            if(seleccionarBoton!=null)
            {
                if(seleccionar)
                {
                    this.seleccionarBoton.setSelected(true);
                }
                else
                {
                    this.seleccionarBoton.setSelected(false);
                }
            }

            if(punteadoBoton!=null)
            {
                if(trazoPunteado)
                {
                    this.punteadoBoton.setSelected(true);
                }
                else
                {
                    this.punteadoBoton.setSelected(false);
                }
            }

            if(degradadoCheckbox!=null)
            {
                if(this.degradado)
                {
                    this.degradadoCheckbox.setSelected(true);
                }
                else
                {
                    this.degradadoCheckbox.setSelected(false);
                }
            }

            if(paletaTrazo!=null)
            {
                paletaTrazo.setBackground(this.colorTrazo);
            }

            if(paletaRelleno!=null)
            {
                paletaRelleno.setBackground(this.colorRelleno);
            }

            if(paletaFondo!=null)
            {
                paletaFondo.setBackground(this.colorFondo);
            }

            if(paletaFrente!=null)
            {
                paletaFrente.setBackground(this.colorFrente);
            }
            
            if(grosorCombobox!=null)
            {
                grosorCombobox.setSelectedIndex((int)this.grosorTrazo-1);
            }

            if(verticalCheckbox!=null)
            {
                verticalCheckbox.setSelected(this.vertical);
            }

            if(horizontalCheckbox!=null)
            {
                horizontalCheckbox.setSelected(this.horizontal);
            }
        }
    }

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

