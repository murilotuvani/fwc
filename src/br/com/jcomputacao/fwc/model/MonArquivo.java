package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class MonArquivo implements Serializable {

    private Date datColeta;
    private long codBancoDados;
    private String nomPontoMontagem;
    private String tipArquivo;
    private String nomArquivo;
    private String nomTablespace;
    private long valUtilizado;
    private long valLivre;
    private long valMaximo;
    private String flgExtensivel;
    private long valIncremento;

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

    public String getNomPontoMontagem() {
        return this.nomPontoMontagem;
    }

    public void setNomPontoMontagem(String nomPontoMontagem) {
        this.nomPontoMontagem = nomPontoMontagem;
    }

    public String getTipArquivo() {
        return this.tipArquivo;
    }

    public void setTipArquivo(String tipArquivo) {
        this.tipArquivo = tipArquivo;
    }

    public String getNomArquivo() {
        return this.nomArquivo;
    }

    public void setNomArquivo(String nomArquivo) {
        this.nomArquivo = nomArquivo;
    }

    public String getNomTablespace() {
        return this.nomTablespace;
    }

    public void setNomTablespace(String nomTablespace) {
        this.nomTablespace = nomTablespace;
    }

    public long getValUtilizado() {
        return this.valUtilizado;
    }

    public void setValUtilizado(long valUtilizado) {
        this.valUtilizado = valUtilizado;
    }

    public long getValLivre() {
        return this.valLivre;
    }

    public void setValLivre(long valLivre) {
        this.valLivre = valLivre;
    }

    public long getValMaximo() {
        return this.valMaximo;
    }

    public void setValMaximo(long valMaximo) {
        this.valMaximo = valMaximo;
    }

    public String getFlgExtensivel() {
        return this.flgExtensivel;
    }

    public void setFlgExtensivel(String flgExtensivel) {
        this.flgExtensivel = flgExtensivel;
    }

    public long getValIncremento() {
        return this.valIncremento;
    }

    public void setValIncremento(long valIncremento) {
        this.valIncremento = valIncremento;
    }
}
