package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class ClienteAgendamento implements Serializable {

    private long codAgendamento;
    private long codCliente;
    private long codOcorrencia;
    private String nomUsuario;
    private String tipAgendamento;
    private String flgRealizado;

    public long getCodAgendamento() {
        return this.codAgendamento;
    }

    public void setCodAgendamento(long codAgendamento) {
        this.codAgendamento = codAgendamento;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public long getCodOcorrencia() {
        return this.codOcorrencia;
    }

    public void setCodOcorrencia(long codOcorrencia) {
        this.codOcorrencia = codOcorrencia;
    }

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getTipAgendamento() {
        return this.tipAgendamento;
    }

    public void setTipAgendamento(String tipAgendamento) {
        this.tipAgendamento = tipAgendamento;
    }

    public String getFlgRealizado() {
        return this.flgRealizado;
    }

    public void setFlgRealizado(String flgRealizado) {
        this.flgRealizado = flgRealizado;
    }
}
