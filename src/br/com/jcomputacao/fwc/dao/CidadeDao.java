package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Cidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CidadeDao {

    public String getSelect() {
        return "SELECT C.COD_CIDADE,C.COD_ESTADO,C.NOM_CIDADE FROM CIDADE C";
    }

    public String getInsert() {
        return "INSERT INTO CIDADE (COD_CIDADE,COD_ESTADO,NOM_CIDADE) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CIDADE SET COD_CIDADE=?,COD_ESTADO=?,NOM_CIDADE=?";
    }

    public String getDelete() {
        return "DELETE FROM CIDADE WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Cidade c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodCidade());
        stmt.setLong(idx++, c.getCodEstado());
        stmt.setString(idx++, c.getNomCidade());
    }

    protected void setValues(Cidade c, ResultSet rs) throws SQLException {
        c.setCodCidade(rs.getLong("COD_CIDADE"));
        c.setCodEstado(rs.getLong("COD_ESTADO"));
        c.setNomCidade(rs.getString("NOM_CIDADE"));
    }
}
