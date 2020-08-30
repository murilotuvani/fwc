package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonBackup implements Serializable {

    private long codBackup;
    private Date datColeta;
    private long codBancoDados;
    private String tipBackup;
    private Date datInicio;
    private Date datFim;
    private long valBytesEntrada;
    private long valBytesSaida;
    private long codRetorno;

    public long getCodBackup() {
        return this.codBackup;
    }

    public void setCodBackup(long codBackup) {
        this.codBackup = codBackup;
    }

    public Date getDatColeta() {
        return this.datColeta;
    }

    public void setDatColeta(Date datColeta) {
        this.datColeta = datColeta;
    }

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public String getTipBackup() {
        return this.tipBackup;
    }

    public void setTipBackup(String tipBackup) {
        this.tipBackup = tipBackup;
    }

    public Date getDatInicio() {
        return this.datInicio;
    }

    public void setDatInicio(Date datInicio) {
        this.datInicio = datInicio;
    }

    public Date getDatFim() {
        return this.datFim;
    }

    public void setDatFim(Date datFim) {
        this.datFim = datFim;
    }

    public long getValBytesEntrada() {
        return this.valBytesEntrada;
    }

    public void setValBytesEntrada(long valBytesEntrada) {
        this.valBytesEntrada = valBytesEntrada;
    }

    public long getValBytesSaida() {
        return this.valBytesSaida;
    }

    public void setValBytesSaida(long valBytesSaida) {
        this.valBytesSaida = valBytesSaida;
    }

    public long getCodRetorno() {
        return this.codRetorno;
    }

    public void setCodRetorno(long codRetorno) {
        this.codRetorno = codRetorno;
    }
}
