package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class MonSpam implements Serializable {

    private long codMensagem;
    private long numLinha;
    private String valLinha;

    public long getCodMensagem() {
        return this.codMensagem;
    }

    public void setCodMensagem(long codMensagem) {
        this.codMensagem = codMensagem;
    }

    public long getNumLinha() {
        return this.numLinha;
    }

    public void setNumLinha(long numLinha) {
        this.numLinha = numLinha;
    }

    public String getValLinha() {
        return this.valLinha;
    }

    public void setValLinha(String valLinha) {
        this.valLinha = valLinha;
    }
}
