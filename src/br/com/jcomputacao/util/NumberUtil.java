package br.com.jcomputacao.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 14/02/2010 14:08:48
 * @author Murilo
 */
public class NumberUtil {
    private static final NumberFormat decimalBanco = NumberFormat.getNumberInstance(Locale.US);

    static {
        decimalBanco.setMaximumFractionDigits(2);
        decimalBanco.setMinimumFractionDigits(2);
        decimalBanco.setGroupingUsed(false);
    }

    public static int soma(int [] vetor){
        int soma = 0;
        for(int posicao=0;posicao<vetor.length;posicao++){
            soma += vetor[posicao];
        }
        return soma;
    }

    public static String decimalBanco(double valor){
        return decimalBanco.format(valor);
    }

    public static double getDouble(String text) {
        if(text==null || "".equals(text)) {
            return 0d;
        } else {
            text = text.replace(",", ".");
            double valor = 0d;
            try {
                valor = decimalBanco.parse(text).doubleValue();
            } catch(NumberFormatException ex) {
                ex.printStackTrace();
            } finally {
                return valor;
            }
        }
    }

    public static boolean isNullOrEmpty(Integer intt) {
        return (intt == null || "".equals(intt));
    }
    public static boolean isNullOrEmpty(boolean   boleano) {
        return (boleano == false);
    }

}
