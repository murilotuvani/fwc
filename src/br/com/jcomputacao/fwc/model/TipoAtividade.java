package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class TipoAtividade implements Serializable {

    private long codTipoAtividade;
    private String nomTipoAtividade;

    public long getCodTipoAtividade() {
        return this.codTipoAtividade;
    }

    public void setCodTipoAtividade(long codTipoAtividade) {
        this.codTipoAtividade = codTipoAtividade;
    }

    public String getNomTipoAtividade() {
        return this.nomTipoAtividade;
    }

    public void setNomTipoAtividade(String nomTipoAtividade) {
        this.nomTipoAtividade = nomTipoAtividade;
    }
}
