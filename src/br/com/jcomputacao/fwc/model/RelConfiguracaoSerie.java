/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcomputacao.fwc.model;

/**
 *
 * @author Felipe Caue 19/10/2011 09:50:31
 */
public class RelConfiguracaoSerie {

    private int numeroSerie;
    private int codigoConfiguracaoSerie;
    private int codigoConfiguracao;
    private Integer corDaSerie;

    public int getCodigoConfiguracao() {
        return codigoConfiguracao;
    }

    public void setCodigoConfiguracao(int codigoConfiguracao) {
        this.codigoConfiguracao = codigoConfiguracao;
    }

    public int getCodigoConfiguracaoSerie() {
        return codigoConfiguracaoSerie;
    }

    public void setCodigoConfiguracaoSerie(int codigoConfiguracaoSerie) {
        this.codigoConfiguracaoSerie = codigoConfiguracaoSerie;
    }

    public Integer getCorDaSerie() {
        return corDaSerie;
    }

    public void setCorDaSerie(Integer corDaSerie) {
        this.corDaSerie = corDaSerie;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
}
