package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class MonBancoDadosServidor implements Serializable {

    private long codBancoDados;
    private long codServidor;
    private long instanceNumber;

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }

    public long getInstanceNumber() {
        return this.instanceNumber;
    }

    public void setInstanceNumber(long instanceNumber) {
        this.instanceNumber = instanceNumber;
    }
}
