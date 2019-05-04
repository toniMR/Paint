package sm.amr.IU;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JCheckBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antonio
 */
public class Lienzo2DImagen extends Lienzo {

    private BufferedImage img;
    /**
     * Creates new form Lienzo2DImagen
     */
    public Lienzo2DImagen() {
        initComponents();
    }
    
    /**
     * Establece la imagen que tendra el lienzo
     * @param img 
     */
    public void setImage(BufferedImage img){
        this.img = img;
        if(img!=null)
        {
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        }
    }
    
    /**
     * Devuelve la imagen del lienzo
     * @return 
     */
    public BufferedImage getImage(){
        return img;
    }
    
    /**
     * Devuelve la imagen del lienzo
     * @param drawVector si es true creará los graphics para esa imagen
     * @return BufferedImage 
     */
    public BufferedImage getImage(boolean drawVector)
    {
        if (drawVector)
        {
            BufferedImage imgOut = new BufferedImage (img.getWidth(), img.getHeight(), img.getType());
            // EN ESTA LINEA HABRA QUE PONER ALGO PARA LAS TRANSPARENCIAS
            // (Si es opaco, si no lo es, etc)
            this.paint(imgOut.createGraphics());
            return(imgOut);
        }
        else
        {
            return getImage();
        }
    }
    
    /**
     * Pinta la imagen
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    if(img!=null) g.drawImage(img,0,0,this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}