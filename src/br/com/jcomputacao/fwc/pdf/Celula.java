package br.com.jcomputacao.fwc.pdf;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * 03/07/2011 19:30:43
 * @author Murilo
 */
public class Celula {
    @XmlValue
    String conteudo;
    @XmlAttribute(name="colspan")
    int colspan = 1;
    @XmlAttribute(name="align")
    String align;
    @XmlAttribute(name="width")
    String width;
}
