package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class RelRelatorioServidor implements Serializable {

    private long codRelatorio;
    private long codServidor;

    public long getCodRelatorio() {
        return this.codRelatorio;
    }

    public void setCodRelatorio(long codRelatorio) {
        this.codRelatorio = codRelatorio;
    }

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }
}