package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class MonBancoDados implements Serializable, Comparable<MonBancoDados> {

    private long codBancoDados;
    private long codCliente;
    private long numDbid;
    private String nomBancoDados;
    private String valVersao;
    private long qtdInstances;

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public long getNumDbid() {
        return this.numDbid;
    }

    public void setNumDbid(long numDbid) {
        this.numDbid = numDbid;
    }

    public String getNomBancoDados() {
        return this.nomBancoDados;
    }

    public void setNomBancoDados(String nomBancoDados) {
        this.nomBancoDados = nomBancoDados;
    }

    public String getValVersao() {
        return this.valVersao;
    }

    public void setValVersao(String valVersao) {
        this.valVersao = valVersao;
    }

    public long getQtdInstances() {
        return this.qtdInstances;
    }

    public void setQtdInstances(long qtdInstances) {
        this.qtdInstances = qtdInstances;
    }

    @Override
    public int compareTo(MonBancoDados o) {
        return this.nomBancoDados.compareTo(o.nomBancoDados);
    }
}
