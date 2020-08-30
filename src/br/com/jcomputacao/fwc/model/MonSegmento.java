package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonSegmento implements Serializable {

    private Date datColeta;
    private long codBancoDados;
    private String nomOwner;
    private String tipSegmento;
    private String nomTablespace;
    private long valBytes;

    public Date getDatColeta() {
        return this.datColeta;
    }

    public void setDatColeta(Date datColeta) {
        this.datColeta = datColeta;
    }

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public String getNomOwner() {
        return this.nomOwner;
    }

    public void setNomOwner(String nomOwner) {
        this.nomOwner = nomOwner;
    }

    public String getTipSegmento() {
        return this.tipSegmento;
    }

    public void setTipSegmento(String tipSegmento) {
        this.tipSegmento = tipSegmento;
    }

    public String getNomTablespace() {
        return this.nomTablespace;
    }

    public void setNomTablespace(String nomTablespace) {
        this.nomTablespace = nomTablespace;
    }

    public long getValBytes() {
        return this.valBytes;
    }

    public void setValBytes(long valBytes) {
        this.valBytes = valBytes;
    }
}
