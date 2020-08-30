package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class Cliente implements Serializable, Comparable {

    private long codCliente;
    private String nomCliente;
    private String nomRazaoSocial;
    private String flgMonitoramento;
    private String flgAlert;
    private String nomResponsavel;
    private String desPropostaServico;
    private long numProposta;

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return this.nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getNomRazaoSocial() {
        return this.nomRazaoSocial;
    }

    public void setNomRazaoSocial(String nomRazaoSocial) {
        this.nomRazaoSocial = nomRazaoSocial;
    }

    public String getFlgMonitoramento() {
        return this.flgMonitoramento;
    }

    public void setFlgMonitoramento(String flgMonitoramento) {
        this.flgMonitoramento = flgMonitoramento;
    }

    public String getFlgAlert() {
        return this.flgAlert;
    }

    public void setFlgAlert(String flgAlert) {
        this.flgAlert = flgAlert;
    }

    public String getDesPropostaServico() {
        return desPropostaServico;
    }

    public void setDesPropostaServico(String desPropostaServico) {
        this.desPropostaServico = desPropostaServico;
    }

    public String getNomResponsavel() {
        return nomResponsavel;
    }

    public void setNomResponsavel(String nomResponsavel) {
        this.nomResponsavel = nomResponsavel;
    }

    public long getNumProposta() {
        return numProposta;
    }

    public void setNumProposta(long numProposta) {
        this.numProposta = numProposta;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Cliente) {
            return this.nomCliente.compareTo(((Cliente)o).nomCliente);
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return this.nomCliente;
    }
}
