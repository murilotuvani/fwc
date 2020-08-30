package br.com.jcomputacao.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 23/10/2010 10:18:43
 * @author Murilo
 */
public class ChavePrimariaDescritor {
    private boolean autogerada;
    private List<ChavePrimariaDescritorCampo> campos = new ArrayList<ChavePrimariaDescritorCampo>();

    public boolean isComposta() {
        return campos.size()>1;
    }

    public boolean isAutogerada() {
        return autogerada;
    }

    public void setAutogerada(boolean autogerada) {
        this.autogerada = autogerada;
    }

    public List<ChavePrimariaDescritorCampo> getCampos() {
        return campos;
    }

    public void setCampos(List<ChavePrimariaDescritorCampo> campos) {
        this.campos = campos;
    }

    public void addCampo(String atributo, Class tipo, String colunaSql, int tipoSql) {
        ChavePrimariaDescritorCampo c = new ChavePrimariaDescritorCampo();
        c.setAtributo(atributo);
        c.setTipo(tipo);
        c.setColunaSql(colunaSql);
        c.setTipoSql(tipoSql);
        addCampo(c);
    }

    private void addCampo(ChavePrimariaDescritorCampo c) {
        this.campos.add(c);
    }

}
