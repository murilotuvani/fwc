package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class ClienteContato implements Serializable {

    private long codContato;
    private long codCliente;
    private String tipContato;
    private long numDdd;
    private long numTelefone;
    private String nomContato;
    private long numRamal;
    private String valEmail;

    public long getCodContato() {
        return this.codContato;
    }

    public void setCodContato(long codContato) {
        this.codContato = codContato;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipContato() {
        return this.tipContato;
    }

    public void setTipContato(String tipContato) {
        this.tipContato = tipContato;
    }

    public long getNumDdd() {
        return this.numDdd;
    }

    public void setNumDdd(long numDdd) {
        this.numDdd = numDdd;
    }

    public long getNumTelefone() {
        return this.numTelefone;
    }

    public void setNumTelefone(long numTelefone) {
        this.numTelefone = numTelefone;
    }

    public String getNomContato() {
        return this.nomContato;
    }

    public void setNomContato(String nomContato) {
        this.nomContato = nomContato;
    }

    public long getNumRamal() {
        return this.numRamal;
    }

    public void setNumRamal(long numRamal) {
        this.numRamal = numRamal;
    }

    public String getValEmail() {
        return this.valEmail;
    }

    public void setValEmail(String valEmail) {
        this.valEmail = valEmail;
    }
}
