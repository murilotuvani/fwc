package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class Parametro implements Serializable {

    private String nomParametro;
    private String valParametro;

    public String getNomParametro() {
        return this.nomParametro;
    }

    public void setNomParametro(String nomParametro) {
        this.nomParametro = nomParametro;
    }

    public String getValParametro() {
        return this.valParametro;
    }

    public void setValParametro(String valParametro) {
        this.valParametro = valParametro;
    }
}
