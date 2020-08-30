package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class RelRelatorio implements Serializable {

    private long codRelatorio;
    private long codCliente;
    private String nomUsuario;
    private String nomCliente;
    private Date datCadastro;
    private Date datInicio;
    private Date datFim;

    public long getCodRelatorio() {
        return this.codRelatorio;
    }

    public void setCodRelatorio(long codRelatorio) {
        this.codRelatorio = codRelatorio;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public Date getDatCadastro() {
        return this.datCadastro;
    }

    public void setDatCadastro(Date datCadastro) {
        this.datCadastro = datCadastro;
    }

    public Date getDatInicio() {
        return datInicio;
    }

    public void setDatInicio(Date datInicio) {
        this.datInicio = datInicio;
    }

    public Date getDatFim() {
        return datFim;
    }

    public void setDatFim(Date datFim) {
        this.datFim = datFim;
    }
}