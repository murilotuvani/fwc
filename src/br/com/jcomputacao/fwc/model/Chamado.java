package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Chamado implements Serializable {

    private long numChamado;
    private Date datChamado;
    private long codCliente;
    private long tipChamado;
    private long sitChamado;

    public long getNumChamado() {
        return this.numChamado;
    }

    public void setNumChamado(long numChamado) {
        this.numChamado = numChamado;
    }

    public Date getDatChamado() {
        return this.datChamado;
    }

    public void setDatChamado(Date datChamado) {
        this.datChamado = datChamado;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public long getTipChamado() {
        return this.tipChamado;
    }

    public void setTipChamado(long tipChamado) {
        this.tipChamado = tipChamado;
    }

    public long getSitChamado() {
        return this.sitChamado;
    }

    public void setSitChamado(long sitChamado) {
        this.sitChamado = sitChamado;
    }
}
