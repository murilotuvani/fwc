package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonMemoria implements Serializable {

    private long codServidor;
    private Date datColeta;
    private double valUtilizado;
    private double valDisponivel;

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }

    public Date getDatColeta() {
        return this.datColeta;
    }

    public void setDatColeta(Date datColeta) {
        this.datColeta = datColeta;
    }

    public double getValUtilizado() {
        return this.valUtilizado;
    }

    public void setValUtilizado(double valUtilizado) {
        this.valUtilizado = valUtilizado;
    }

    public double getValDisponivel() {
        return this.valDisponivel;
    }

    public void setValDisponivel(double valDisponivel) {
        this.valDisponivel = valDisponivel;
    }
}
