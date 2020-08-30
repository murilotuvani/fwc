package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class ClienteEnderecoTipo implements Serializable {

    private long codTipo;
    private String nomTipo;
    private String nomTipoAbreviado;

    public long getCodTipo() {
        return this.codTipo;
    }

    public void setCodTipo(long codTipo) {
        this.codTipo = codTipo;
    }

    public String getNomTipo() {
        return this.nomTipo;
    }

    public void setNomTipo(String nomTipo) {
        this.nomTipo = nomTipo;
    }

    public String getNomTipoAbreviado() {
        return this.nomTipoAbreviado;
    }

    public void setNomTipoAbreviado(String nomTipoAbreviado) {
        this.nomTipoAbreviado = nomTipoAbreviado;
    }
}
