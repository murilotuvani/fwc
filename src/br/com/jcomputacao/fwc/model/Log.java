package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {

    private String rotina;
    private Date data;
    private String mensagem;

    public String getRotina() {
        return this.rotina;
    }

    public void setRotina(String rotina) {
        this.rotina = rotina;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
