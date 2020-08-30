/*
 *
 *
 */

package br.com.jcomputacao.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 02/02/2010 18:06:25
 * @author Murilo
 */
public class WindowUtil {
//    public static void centraliza(javax.swing.JInternalFrame iframe){
//        Dimension myDim = iframe.getSize();
//        Dimension dim = Ambiente.getDesktop().getSize();
//        if(myDim.height>(dim.height-30)){
//            myDim.height = dim.height-30;
//            iframe.setSize(myDim);
//        }
//
//        if(myDim.width>dim.width){
//            myDim.width = dim.width;
//            iframe.setSize(myDim);
//        }
//
//        int x = (dim.width/2-myDim.width/2);
//        int y = (dim.height/2-myDim.height/2);
//        // COLOCANDO A JANELA NO CENTRO
//        iframe.setLocation(x, y);
//    }

    public static void centraliza(java.awt.Window window){
        Dimension myDim = window.getSize();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if(myDim.height>(dim.height-30)){
            myDim.height = dim.height-30;
            window.setSize(myDim);
        }

        if(myDim.width>dim.width){
            myDim.width = dim.width;
            window.setSize(myDim);
        }

        int x = (dim.width/2-myDim.width/2);
        int y = (dim.height/2-myDim.height/2);
        // COLOCANDO A JANELA NO MEIO
        window.setLocation(x, y);
    }

}
