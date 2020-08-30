package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

    private String nomUsuario;
    private String valSenha;
    private Date datAlteracao;
    private String nomFuncionario;

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

    public String getNomFuncionario() {
        return this.nomFuncionario;
    }

    public void setNomFuncionario(String nomFuncionario) {
        this.nomFuncionario = nomFuncionario;
    }
}
