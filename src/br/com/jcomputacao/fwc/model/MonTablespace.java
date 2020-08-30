package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonTablespace implements Serializable, Comparable<MonTablespace> {

    private long codBancoDados;
    private Date datColeta;
    private String nomTablespace;
    private double valAlocado;
    private double valLivre;
    private double valMaximo;

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public Date getDatColeta() {
        return this.datColeta;
    }

    public void setDatColeta(Date datColeta) {
        this.datColeta = datColeta;
    }

    public String getNomTablespace() {
        return this.nomTablespace;
    }

    public void setNomTablespace(String nomTablespace) {
        this.nomTablespace = nomTablespace;
    }

    public double getValAlocado() {
        return this.valAlocado;
    }

    public void setValAlocado(double valAlocado) {
        this.valAlocado = valAlocado;
    }

    public double getValLivre() {
        return this.valLivre;
    }

    public void setValLivre(double valLivre) {
        this.valLivre = valLivre;
    }

    public double getValMaximo() {
        return this.valMaximo;
    }

    public void setValMaximo(double valMaximo) {
        this.valMaximo = valMaximo;
    }

    @Override
    public int compareTo(MonTablespace o) {
        return this.datColeta.compareTo(o.datColeta);
    }
}
