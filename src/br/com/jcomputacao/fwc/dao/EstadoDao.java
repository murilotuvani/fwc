package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Estado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoDao {

    public String getSelect() {
        return "SELECT E.COD_ESTADO,E.NOM_ESTADO FROM ESTADO E";
    }

    public String getInsert() {
        return "INSERT INTO ESTADO (COD_ESTADO,NOM_ESTADO) VALUES (?,?)";
    }

    public String getUpdate() {
        return "UPDATE ESTADO SET COD_ESTADO=?,NOM_ESTADO=?";
    }

    public String getDelete() {
        return "DELETE FROM ESTADO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Estado e) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, e.getCodEstado());
        stmt.setString(idx++, e.getNomEstado());
    }

    protected void setValues(Estado e, ResultSet rs) throws SQLException {
        e.setCodEstado(rs.getLong("COD_ESTADO"));
        e.setNomEstado(rs.getString("NOM_ESTADO"));
    }
}
