package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class Cidade implements Serializable {

    private long codCidade;
    private long codEstado;
    private String nomCidade;

    public long getCodCidade() {
        return this.codCidade;
    }

    public void setCodCidade(long codCidade) {
        this.codCidade = codCidade;
    }

    public long getCodEstado() {
        return this.codEstado;
    }

    public void setCodEstado(long codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomCidade() {
        return this.nomCidade;
    }

    public void setNomCidade(String nomCidade) {
        this.nomCidade = nomCidade;
    }
}
