package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonCpu implements Serializable {

    private long codServidor;
    private Date datColeta;
    private double pctUser;
    private double pctNice;
    private double pctSystem;
    private double pctIowait;
    private double pctSteal;
    private double pctIdle;

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

    public double getPctUser() {
        return this.pctUser;
    }

    public void setPctUser(double pctUser) {
        this.pctUser = pctUser;
    }

    public double getPctNice() {
        return this.pctNice;
    }

    public void setPctNice(double pctNice) {
        this.pctNice = pctNice;
    }

    public double getPctSystem() {
        return this.pctSystem;
    }

    public void setPctSystem(double pctSystem) {
        this.pctSystem = pctSystem;
    }

    public double getPctIowait() {
        return this.pctIowait;
    }

    public void setPctIowait(double pctIowait) {
        this.pctIowait = pctIowait;
    }

    public double getPctSteal() {
        return this.pctSteal;
    }

    public void setPctSteal(double pctSteal) {
        this.pctSteal = pctSteal;
    }

    public double getPctIdle() {
        return this.pctIdle;
    }

    public void setPctIdle(double pctIdle) {
        this.pctIdle = pctIdle;
    }
}
