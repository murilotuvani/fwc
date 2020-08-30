package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonDisco implements Serializable {

    private long codServidor;
    private Date datColeta;
    private String nomPontoMontagem;
    private long valTamanho;
    private long valUtilizado;
    private long valDisponivel;
    private String flgAutoextend;

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

    public String getNomPontoMontagem() {
        return this.nomPontoMontagem;
    }

    public void setNomPontoMontagem(String nomPontoMontagem) {
        this.nomPontoMontagem = nomPontoMontagem;
    }

    public long getValTamanho() {
        return this.valTamanho;
    }

    public void setValTamanho(long valTamanho) {
        this.valTamanho = valTamanho;
    }

    public long getValUtilizado() {
        return this.valUtilizado;
    }

    public void setValUtilizado(long valUtilizado) {
        this.valUtilizado = valUtilizado;
    }

    public long getValDisponivel() {
        return this.valDisponivel;
    }

    public void setValDisponivel(long valDisponivel) {
        this.valDisponivel = valDisponivel;
    }

    public String getFlgAutoextend() {
        return this.flgAutoextend;
    }

    public void setFlgAutoextend(String flgAutoextend) {
        this.flgAutoextend = flgAutoextend;
    }
}
