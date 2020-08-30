package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class AlertaDisco implements Serializable {

    private long codServidor;
    private Date datAlerta;

    public long getCodServidor() {
        return this.codServidor;
    }

    public void setCodServidor(long codServidor) {
        this.codServidor = codServidor;
    }

    public Date getDatAlerta() {
        return this.datAlerta;
    }

    public void setDatAlerta(Date datAlerta) {
        this.datAlerta = datAlerta;
    }
}
