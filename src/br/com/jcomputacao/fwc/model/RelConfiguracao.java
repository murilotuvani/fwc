package br.com.jcomputacao.fwc.model;

import br.com.jcomputacao.fwc.chart.GraficoTipo;
import java.io.Serializable;

/**
 * 29/07/2011 00:07:26
 * @author Murilo
 */
public class RelConfiguracao implements Serializable{
    private int codigo;
    private GraficoTipo tipo = GraficoTipo.TODOS;
    private String titulo;
    private Boolean usaGrid;
    private Integer corFundo;
    private Integer corPlot;
    private Boolean exibeBorda;
    private int exibeBordaInt = 0;
    private int usaGridInt=0;

    public int getUsaGridInt() {
        return usaGridInt;
    }

    public int getExibeBordaInt() {
        return exibeBordaInt;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public GraficoTipo getTipo() {
        return tipo;
    }

    public void setTipo(GraficoTipo tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getUsaGrid() {
        return usaGrid;
    }

    public void setUsaGrid(Boolean usaGrid) {
        this.usaGrid = usaGrid;
         if(this.usaGrid){
            usaGridInt = 1;
        }else{
             usaGridInt = 0;
         }
    }

    public Integer getCorFundo() {
        return corFundo;
    }
    
    public java.awt.Color getCorFundoColor() {
        return new java.awt.Color(corFundo);
    }

    public Integer getCorPlot() {
        return corPlot;
    }

    public void setCorPlot(Integer corPlot) {
        this.corPlot = corPlot;
    }
    
    public java.awt.Color getCorPlotColor() {
        return new java.awt.Color(corPlot);
    }

    public void setCorFundo(Integer corFundo) {
        this.corFundo = corFundo;
    }

    public Boolean getExibeBorda() {
        return exibeBorda;
    }

    public void setExibeBorda(Boolean exibeBorda) {
        this.exibeBorda = exibeBorda;
        if(this.exibeBorda){
            exibeBordaInt = 1;
        }else{
            exibeBordaInt = 0;
        }
       
    }

    public Boolean isExibeBorda() {
        return exibeBorda;
    }
}
