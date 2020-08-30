package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.AlertaTablespace;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlertaTablespaceDao {

    public String getSelect() {
        return "SELECT A.COD_BANCO_DADOS,A.COD_CLIENTE,A.NOM_TABLESPACE,A.DAT_ALERTA FROM ALERTA_TABLESPACE A";
    }

    public String getInsert() {
        return "INSERT INTO ALERTA_TABLESPACE (COD_BANCO_DADOS,COD_CLIENTE,NOM_TABLESPACE,DAT_ALERTA) VALUES (?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE ALERTA_TABLESPACE SET COD_BANCO_DADOS=?,COD_CLIENTE=?,NOM_TABLESPACE=?,DAT_ALERTA=?";
    }

    public String getDelete() {
        return "DELETE FROM ALERTA_TABLESPACE WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, AlertaTablespace a) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, a.getCodBancoDados());
        stmt.setLong(idx++, a.getCodCliente());
        stmt.setString(idx++, a.getNomTablespace());
        stmt.setDate(idx++, new java.sql.Date(a.getDatAlerta().getTime()));
    }

    protected void setValues(AlertaTablespace a, ResultSet rs) throws SQLException {
        a.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        a.setCodCliente(rs.getLong("COD_CLIENTE"));
        a.setNomTablespace(rs.getString("NOM_TABLESPACE"));
        a.setDatAlerta(rs.getDate("DAT_ALERTA"));
    }
}
