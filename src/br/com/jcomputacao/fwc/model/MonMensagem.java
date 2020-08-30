package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonMensagem implements Serializable {

    private long codMensagem;
    private Date datRecebimento;
    private long numLinha;
    private long codCliente;
    private String nomHost;
    private long numDbid;
    private long tipMensagem;
    private Date datEnvio;
    private String valLinha;

    public long getCodMensagem() {
        return this.codMensagem;
    }

    public void setCodMensagem(long codMensagem) {
        this.codMensagem = codMensagem;
    }

    public Date getDatRecebimento() {
        return this.datRecebimento;
    }

    public void setDatRecebimento(Date datRecebimento) {
        this.datRecebimento = datRecebimento;
    }

    public long getNumLinha() {
        return this.numLinha;
    }

    public void setNumLinha(long numLinha) {
        this.numLinha = numLinha;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomHost() {
        return this.nomHost;
    }

    public void setNomHost(String nomHost) {
        this.nomHost = nomHost;
    }

    public long getNumDbid() {
        return this.numDbid;
    }

    public void setNumDbid(long numDbid) {
        this.numDbid = numDbid;
    }

    public long getTipMensagem() {
        return this.tipMensagem;
    }

    public void setTipMensagem(long tipMensagem) {
        this.tipMensagem = tipMensagem;
    }

    public Date getDatEnvio() {
        return this.datEnvio;
    }

    public void setDatEnvio(Date datEnvio) {
        this.datEnvio = datEnvio;
    }

    public String getValLinha() {
        return this.valLinha;
    }

    public void setValLinha(String valLinha) {
        this.valLinha = valLinha;
    }
}
