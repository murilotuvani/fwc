package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Chamado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamadoDao {

    public String getSelect() {
        return "SELECT C.NUM_CHAMADO,C.DAT_CHAMADO,C.COD_CLIENTE,C.TIP_CHAMADO,C.SIT_CHAMADO FROM CHAMADO C";
    }

    public String getInsert() {
        return "INSERT INTO CHAMADO (NUM_CHAMADO,DAT_CHAMADO,COD_CLIENTE,TIP_CHAMADO,SIT_CHAMADO) VALUES (?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CHAMADO SET NUM_CHAMADO=?,DAT_CHAMADO=?,COD_CLIENTE=?,TIP_CHAMADO=?,SIT_CHAMADO=?";
    }

    public String getDelete() {
        return "DELETE FROM CHAMADO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Chamado c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getNumChamado());
        stmt.setDate(idx++, new java.sql.Date(c.getDatChamado().getTime()));
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setLong(idx++, c.getTipChamado());
        stmt.setLong(idx++, c.getSitChamado());
    }

    protected void setValues(Chamado c, ResultSet rs) throws SQLException {
        c.setNumChamado(rs.getLong("NUM_CHAMADO"));
        c.setDatChamado(rs.getDate("DAT_CHAMADO"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setTipChamado(rs.getLong("TIP_CHAMADO"));
        c.setSitChamado(rs.getLong("SIT_CHAMADO"));
    }
}
