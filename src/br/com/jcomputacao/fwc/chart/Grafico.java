/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcomputacao.fwc.chart;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 * 01/06/2011 20:57:39
 * @author Murilo
 */
public class Grafico {
    protected final String escalas [] = new String[]{"bytes", "Kbytes", "Mbytes", "GBytes", "TBytes", "PBytes"};
    byte[] content;
    String titulo;

 
    public byte[] getContent() {
        return content;
    }

    public String getTitulo() {
        return titulo;
    }   
    
    protected byte[] getAsByteArray(JFreeChart chart) {
        int width  = 696;
        int height = 458;
        return getAsByteArray(chart, width, height);
    }
    
    protected byte[] getAsByteArray(JFreeChart chart, int width, int height) {    
        BufferedImage bimage = chart.createBufferedImage(width, height);
        byte [] buf = null;
        try {
            buf = ChartUtilities.encodeAsPNG(bimage);
        } catch (IOException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
        }
        return buf;
    }
}
