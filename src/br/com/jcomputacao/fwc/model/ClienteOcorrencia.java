package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class ClienteOcorrencia implements Serializable {

    private long codOcorrencia;
    private long codCliente;
    private String nomUsuario;
    private Date datOcorrencia;
    private String valObs;
    private long codOcorrenciaPai;

    public long getCodOcorrencia() {
        return this.codOcorrencia;
    }

    public void setCodOcorrencia(long codOcorrencia) {
        this.codOcorrencia = codOcorrencia;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public Date getDatOcorrencia() {
        return this.datOcorrencia;
    }

    public void setDatOcorrencia(Date datOcorrencia) {
        this.datOcorrencia = datOcorrencia;
    }

    public String getValObs() {
        return this.valObs;
    }

    public void setValObs(String valObs) {
        this.valObs = valObs;
    }

    public long getCodOcorrenciaPai() {
        return this.codOcorrenciaPai;
    }

    public void setCodOcorrenciaPai(long codOcorrenciaPai) {
        this.codOcorrenciaPai = codOcorrenciaPai;
    }
}
