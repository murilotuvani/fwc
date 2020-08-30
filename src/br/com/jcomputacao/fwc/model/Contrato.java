package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable {

    private long codContrato;
    private long codCliente;
    private String tipContrato;
    private Date datInicio;
    private Date datFim;
    private long valContrato;

    public long getCodContrato() {
        return this.codContrato;
    }

    public void setCodContrato(long codContrato) {
        this.codContrato = codContrato;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipContrato() {
        return this.tipContrato;
    }

    public void setTipContrato(String tipContrato) {
        this.tipContrato = tipContrato;
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

    public long getValContrato() {
        return this.valContrato;
    }

    public void setValContrato(long valContrato) {
        this.valContrato = valContrato;
    }
}
