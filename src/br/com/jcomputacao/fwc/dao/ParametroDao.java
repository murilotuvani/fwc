package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Parametro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParametroDao {

    public String getSelect() {
        return "SELECT P.NOM_PARAMETRO,P.VAL_PARAMETRO FROM PARAMETRO P";
    }

    public String getInsert() {
        return "INSERT INTO PARAMETRO (NOM_PARAMETRO,VAL_PARAMETRO) VALUES (?,?)";
    }

    public String getUpdate() {
        return "UPDATE PARAMETRO SET NOM_PARAMETRO=?,VAL_PARAMETRO=?";
    }

    public String getDelete() {
        return "DELETE FROM PARAMETRO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Parametro p) throws SQLException {
        int idx = 1;
        stmt.setString(idx++, p.getNomParametro());
        stmt.setString(idx++, p.getValParametro());
    }

    protected void setValues(Parametro p, ResultSet rs) throws SQLException {
        p.setNomParametro(rs.getString("NOM_PARAMETRO"));
        p.setValParametro(rs.getString("VAL_PARAMETRO"));
    }
}
