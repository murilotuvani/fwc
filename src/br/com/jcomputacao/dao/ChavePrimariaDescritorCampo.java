package br.com.jcomputacao.dao;

/**
 * 23/10/2010 10:22:11
 * @author Murilo
 */
public class ChavePrimariaDescritorCampo {
    private String atributo;
    private Class tipo;
    private String colunaSql;
    private int tipoSql;

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public Class getTipo() {
        return tipo;
    }

    public void setTipo(Class tipo) {
        this.tipo = tipo;
    }

    public String getColunaSql() {
        return colunaSql;
    }

    public void setColunaSql(String colunaSql) {
        this.colunaSql = colunaSql;
    }

    /**
     * Este metodo deve retornar o tipo SQL
     * conforme especificado na classe java.sql.Types
     * @return
     */
    public int getTipoSql() {
        return tipoSql;
    }

    public void setTipoSql(int tipoSql) {
        this.tipoSql = tipoSql;
    }
}
