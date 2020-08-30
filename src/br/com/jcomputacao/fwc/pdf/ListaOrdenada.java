package br.com.jcomputacao.fwc.pdf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 06/07/2011 11:48:36
 * @author Murilo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ol")
public class ListaOrdenada {
    @XmlElement(name = "li")
    List<ListaItem> linhas = new ArrayList<ListaItem>();

    public List<ListaItem> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<ListaItem> linhas) {
        this.linhas = linhas;
    }    
}
