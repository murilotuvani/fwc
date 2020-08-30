package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class UsuarioBkp implements Serializable {

    private String nomUsuario;
    private String valSenha;
    private Date datAlteracao;

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getValSenha() {
        return this.valSenha;
    }

    public void setValSenha(String valSenha) {
        this.valSenha = valSenha;
    }

    public Date getDatAlteracao() {
        return this.datAlteracao;
    }

    public void setDatAlteracao(Date datAlteracao) {
        this.datAlteracao = datAlteracao;
    }
}
