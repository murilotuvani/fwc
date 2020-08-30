package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonDisponibilidade implements Serializable, Comparable<MonDisponibilidade> {

    private Date datEvento;
    private Date datRecebimento;
    private long codServidor;
    private long codBancoDados;
    private String desEvento;
    private long instanceNumber;

    public Date getDatEvento() {
        return this.datEvento;
    }

    public void setDatEvento(Date datEvento) {
        this.datEvento = datEvento;
    }

    public Date getDatRecebimento() {
        return this.datRecebimento;
    }

    public void setDatRecebimento(Date datRecebimento) {
        this.datRecebimento = datRecebimento;
    }

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public String getDesEvento() {
        return this.desEvento;
    }

    public void setDesEvento(String desEvento) {
        this.desEvento = desEvento;
    }

    public long getInstanceNumber() {
        return this.instanceNumber;
    }

    public void setInstanceNumber(long instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    @Override
    public int compareTo(MonDisponibilidade o) {
        return this.datEvento.compareTo(o.datEvento);
    }
}
