package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonAlert implements Serializable {

    private long codBancoDados;
    private Date datColeta;
    private long numLinha;
    private String valErro;

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

    public long getNumLinha() {
        return this.numLinha;
    }

    public void setNumLinha(long numLinha) {
        this.numLinha = numLinha;
    }

    public String getValErro() {
        return this.valErro;
    }

    public void setValErro(String valErro) {
        this.valErro = valErro;
    }
}
