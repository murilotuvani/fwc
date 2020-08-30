package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class MonErroBackup implements Serializable {

    private long codBackup;
    private long numLinha;
    private String valLinha;

    public long getCodBackup() {
        return this.codBackup;
    }

    public void setCodBackup(long codBackup) {
        this.codBackup = codBackup;
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
