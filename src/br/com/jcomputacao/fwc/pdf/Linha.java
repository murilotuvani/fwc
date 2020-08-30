package br.com.jcomputacao.fwc.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 * 03/07/2011 19:30:55
 * @author Murilo
 */
public class Linha {

    @XmlElement(name = "td")
    List<Celula> celulas = new ArrayList<Celula>();

    int getColunas() {
        int colunas = 0;
        for (Celula celula : celulas) {
            colunas += celula.colspan;
        }
        return colunas;
    }
}
