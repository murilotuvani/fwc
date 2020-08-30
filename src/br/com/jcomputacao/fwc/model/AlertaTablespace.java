package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class AlertaTablespace implements Serializable {

    private long codBancoDados;
    private long codCliente;
    private String nomTablespace;
    private Date datAlerta;

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomTablespace() {
        return this.nomTablespace;
    }

    public void setNomTablespace(String nomTablespace) {
        this.nomTablespace = nomTablespace;
    }

    public Date getDatAlerta() {
        return this.datAlerta;
    }

    public void setDatAlerta(Date datAlerta) {
        this.datAlerta = datAlerta;
    }
}
