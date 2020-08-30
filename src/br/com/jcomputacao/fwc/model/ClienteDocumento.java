package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class ClienteDocumento implements Serializable {

    private long codDocumento;
    private long codCliente;
    private String tipDocumento;
    private long numDocumento;
    private long numDigito;

    public long getCodDocumento() {
        return this.codDocumento;
    }

    public void setCodDocumento(long codDocumento) {
        this.codDocumento = codDocumento;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipDocumento() {
        return this.tipDocumento;
    }

    public void setTipDocumento(String tipDocumento) {
        this.tipDocumento = tipDocumento;
    }

    public long getNumDocumento() {
        return this.numDocumento;
    }

    public void setNumDocumento(long numDocumento) {
        this.numDocumento = numDocumento;
    }

    public long getNumDigito() {
        return this.numDigito;
    }

    public void setNumDigito(long numDigito) {
        this.numDigito = numDigito;
    }
}
