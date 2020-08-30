package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class ClienteEndereco implements Serializable {

    private long codEndereco;
    private long codTipo;
    private long codCliente;
    private long tipEndereco;
    private String nomLogradouro;
    private String nomBairro;
    private long codCidade;
    private long numCep;

    public long getCodEndereco() {
        return this.codEndereco;
    }

    public void setCodEndereco(long codEndereco) {
        this.codEndereco = codEndereco;
    }

    public long getCodTipo() {
        return this.codTipo;
    }

    public void setCodTipo(long codTipo) {
        this.codTipo = codTipo;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public long getTipEndereco() {
        return this.tipEndereco;
    }

    public void setTipEndereco(long tipEndereco) {
        this.tipEndereco = tipEndereco;
    }

    public String getNomLogradouro() {
        return this.nomLogradouro;
    }

    public void setNomLogradouro(String nomLogradouro) {
        this.nomLogradouro = nomLogradouro;
    }

    public String getNomBairro() {
        return this.nomBairro;
    }

    public void setNomBairro(String nomBairro) {
        this.nomBairro = nomBairro;
    }

    public long getCodCidade() {
        return this.codCidade;
    }

    public void setCodCidade(long codCidade) {
        this.codCidade = codCidade;
    }

    public long getNumCep() {
        return this.numCep;
    }

    public void setNumCep(long numCep) {
        this.numCep = numCep;
    }
}
