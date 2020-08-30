package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Atividade implements Serializable {

    private long codAtividade;
    private long codTipoAtividade;
    private long numChamado;
    private long codCliente;
    private long codServidor;
    private Date datInicio;
    private Date datFim;
    private long horIntervalo;
    private String desMotivo;
    private String desAcaoTomada;
    private String nomUsuario;
    private String flgContrato;
    private String flgInterno;

    public long getCodAtividade() {
        return this.codAtividade;
    }

    public void setCodAtividade(long codAtividade) {
        this.codAtividade = codAtividade;
    }

    public long getCodTipoAtividade() {
        return this.codTipoAtividade;
    }

    public void setCodTipoAtividade(long codTipoAtividade) {
        this.codTipoAtividade = codTipoAtividade;
    }

    public long getNumChamado() {
        return this.numChamado;
    }

    public void setNumChamado(long numChamado) {
        this.numChamado = numChamado;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public long getCodServidor() {
        return codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
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

    public long getHorIntervalo() {
        return this.horIntervalo;
    }

    public void setHorIntervalo(long horIntervalo) {
        this.horIntervalo = horIntervalo;
    }

    public String getDesMotivo() {
        return this.desMotivo;
    }

    public void setDesMotivo(String desMotivo) {
        this.desMotivo = desMotivo;
    }

    public String getDesAcaoTomada() {
        return this.desAcaoTomada;
    }

    public void setDesAcaoTomada(String desAcaoTomada) {
        this.desAcaoTomada = desAcaoTomada;
    }

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getFlgContrato() {
        return this.flgContrato;
    }

    public void setFlgContrato(String flgContrato) {
        this.flgContrato = flgContrato;
    }

    public String getFlgInterno() {
        return this.flgInterno;
    }

    public void setFlgInterno(String flgInterno) {
        this.flgInterno = flgInterno;
    }
}
