package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.AlertaDisco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlertaDiscoDao {

    public String getSelect() {
        return "SELECT A.COD_SERVIDOR,A.DAT_ALERTA FROM ALERTA_DISCO A";
    }

    public String getInsert() {
        return "INSERT INTO ALERTA_DISCO (COD_SERVIDOR,DAT_ALERTA) VALUES (?,?)";
    }

    public String getUpdate() {
        return "UPDATE ALERTA_DISCO SET COD_SERVIDOR=?,DAT_ALERTA=?";
    }

    public String getDelete() {
        return "DELETE FROM ALERTA_DISCO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, AlertaDisco a) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, a.getCodServidor());
        stmt.setDate(idx++, new java.sql.Date(a.getDatAlerta().getTime()));
    }

    protected void setValues(AlertaDisco a, ResultSet rs) throws SQLException {
        a.setCodServidor(rs.getLong("COD_SERVIDOR"));
        a.setDatAlerta(rs.getDate("DAT_ALERTA"));
    }
}
