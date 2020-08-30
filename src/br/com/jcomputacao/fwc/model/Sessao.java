package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Sessao implements Serializable {

    private long idSessao;
    private String nomUsuario;
    private Date datCriacao;

    public long getIdSessao() {
        return this.idSessao;
    }

    public void setIdSessao(long idSessao) {
        this.idSessao = idSessao;
    }

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public Date getDatCriacao() {
        return this.datCriacao;
    }

    public void setDatCriacao(Date datCriacao) {
        this.datCriacao = datCriacao;
    }
}
