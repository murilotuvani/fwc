package br.com.jcomputacao.fwc.model;

import br.com.jcomputacao.fwc.chart.GraficoTipo;
import java.io.Serializable;

/**
 *
 * @author Felipe Caue 30/08/2011 11:51:39
 */
public class Configuracao implements Serializable {

    private int corDeFundo;
    private int areaDePlotagem;
    private int codConfiguracao;
    private String descricaoTipo;
    private String descricaoTitulo;
    private int bolUsaGgrid;
    private int corPlotagen;
    private int bolUsaBorda;

    public String getDescricaoTitulo() {
        return descricaoTitulo;
    }

    public void setDescricaoTitulo(String descricaoTitulo) {
        this.descricaoTitulo = descricaoTitulo;
    }

    public int getBolUsaBorda() {
        return bolUsaBorda;
    }

    public void setBolUsaBorda(int bolUsaBorda) {
        this.bolUsaBorda = bolUsaBorda;
    }

    public int getBolUsaGgrid() {
        return bolUsaGgrid;
    }

    public void setBolUsaGgrid(int bolUsaGgrid) {
        this.bolUsaGgrid = bolUsaGgrid;
    }

    public int getCorPlotagen() {
        return corPlotagen;
    }

    public void setCorPlotagen(int corPlotagen) {
        this.corPlotagen = corPlotagen;
    }

    public String getDescricaoTipo() {
        return descricaoTipo;
    }

    public void setDescricaoTipo(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
    }

    public int getCodConfiguracao() {
        return codConfiguracao;
    }

    public void setCodConfiguracao(int codConfiguracao) {
        this.codConfiguracao = codConfiguracao;
    }

    public int getAreaDePlotagem() {
        return areaDePlotagem;
    }

    public void setAreaDePlotagem(int areaDePlotagem) {
        this.areaDePlotagem = areaDePlotagem;
    }

    public int getCorDeFundo() {
        return corDeFundo;
    }

    public void setCorDeFundo(int corDeFundo) {
        this.corDeFundo = corDeFundo;
    }

}
