package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MonServidor implements Serializable, Comparable {

    private long codServidor;
    private long codCliente;
    private String nomServidor;
    private String flgAtivo;
    private String tipSistemaOperacional;
    private Set<MonBancoDados> instancias = null;
    private String nomCliente;
    private Set<MonDisco> discos;

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }

    public long getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomServidor() {
        return this.nomServidor;
    }

    public void setNomServidor(String nomServidor) {
        this.nomServidor = nomServidor;
    }

    public String getFlgAtivo() {
        return this.flgAtivo;
    }

    public void setFlgAtivo(String flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    public String getTipSistemaOperacional() {
        return this.tipSistemaOperacional;
    }

    public void setTipSistemaOperacional(String tipSistemaOperacional) {
        this.tipSistemaOperacional = tipSistemaOperacional;
    }

    public Set<MonDisco> getDiscos() {
        return discos;
    }

    public void setDiscos(Set<MonDisco> discos) {
        this.discos = discos;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public Set<MonBancoDados> getInstancias() {
        if (instancias == null) {
            instancias = new TreeSet<MonBancoDados>();
        }
        return instancias;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof MonServidor) {
            return this.nomServidor.compareTo(((MonServidor)o).nomServidor);
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return this.nomServidor;
    }

}
