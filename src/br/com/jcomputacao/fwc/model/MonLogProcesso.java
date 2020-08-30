package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonLogProcesso implements Serializable {

    private long codBancoDados;
    private long codServidor;
    private String nomProcesso;
    private Date datInicio;
    private Date datFim;
    private String desErro;

    public long getCodBancoDados() {
        return this.codBancoDados;
    }

    public void setCodBancoDados(long codBancoDados) {
        this.codBancoDados = codBancoDados;
    }

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }

    public String getNomProcesso() {
        return this.nomProcesso;
    }

    public void setNomProcesso(String nomProcesso) {
        this.nomProcesso = nomProcesso;
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

    public String getDesErro() {
        return this.desErro;
    }

    public void setDesErro(String desErro) {
        this.desErro = desErro;
    }
}
