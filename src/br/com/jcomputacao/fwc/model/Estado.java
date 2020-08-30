package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class Estado implements Serializable {

    private long codEstado;
    private String nomEstado;

    public long getCodEstado() {
        return this.codEstado;
    }

    public void setCodEstado(long codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomEstado() {
        return this.nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }
}
