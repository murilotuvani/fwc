package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

/**
 * 30/06/2011 16:48:12
 * @author Murilo
 */
public class RelElemento implements Comparable<RelElemento>, Serializable {

    private long codRelatorio;
    private String titulo;
    private long codElemento;
    private long numOrdem;
    private String desAnalise;
    private int pagina;
    

    public long getCodRelatorio() {
        return this.codRelatorio;
    }

    public void setCodRelatorio(long codRelatorio) {
        this.codRelatorio = codRelatorio;
    }
    
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getCodElemento() {
        return this.codElemento;
    }

    public void setCodElemento(long codGrafico) {
        this.codElemento = codGrafico;
    }

    public long getNumOrdem() {
        return this.numOrdem;
    }

    public void setNumOrdem(long numOrdem) {
        this.numOrdem = numOrdem;
    }

    public String getDesAnalise() {
        return this.desAnalise;
    }

    public void setDesAnalise(String desAnalise) {
        this.desAnalise = desAnalise;
    }

    @Override
    public int compareTo(RelElemento o) {
        return (int) (this.numOrdem - o.numOrdem);
    }

    /**
     * Pagina especificada em tempo de execucao pelo iText
     * para colocar no indice
     */
    public int getPagina() {
        if(pagina==0) {
            pagina = 1;
        }
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }
}
