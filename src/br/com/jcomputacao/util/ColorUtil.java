package br.com.jcomputacao.util;

import java.awt.Color;

/**
 *
 * @author Felipe Caue 06/09/2011 11:16:10
 */
public class ColorUtil {
    
    public static String rgbToHex(int rgb) {
        return Integer.toHexString(rgb).substring(2).toUpperCase();
    }

    public static int hexToRgb(String hex) {
        String comRash = "#"+hex;
        return Color.decode(comRash).getRGB();
        
    }
}
