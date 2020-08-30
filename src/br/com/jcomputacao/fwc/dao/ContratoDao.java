package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Contrato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContratoDao {

    public String getSelect() {
        return "SELECT C.COD_CONTRATO,C.COD_CLIENTE,C.TIP_CONTRATO,C.DAT_INICIO,C.DAT_FIM,C.VAL_CONTRATO FROM CONTRATO C";
    }

    public String getInsert() {
        return "INSERT INTO CONTRATO (COD_CONTRATO,COD_CLIENTE,TIP_CONTRATO,DAT_INICIO,DAT_FIM,VAL_CONTRATO) VALUES (?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CONTRATO SET COD_CONTRATO=?,COD_CLIENTE=?,TIP_CONTRATO=?,DAT_INICIO=?,DAT_FIM=?,VAL_CONTRATO=?";
    }

    public String getDelete() {
        return "DELETE FROM CONTRATO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Contrato c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodContrato());
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setString(idx++, c.getTipContrato());
        stmt.setDate(idx++, new java.sql.Date(c.getDatInicio().getTime()));
        stmt.setDate(idx++, new java.sql.Date(c.getDatFim().getTime()));
        stmt.setLong(idx++, c.getValContrato());
    }

    protected void setValues(Contrato c, ResultSet rs) throws SQLException {
        c.setCodContrato(rs.getLong("COD_CONTRATO"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setTipContrato(rs.getString("TIP_CONTRATO"));
        c.setDatInicio(rs.getDate("DAT_INICIO"));
        c.setDatFim(rs.getDate("DAT_FIM"));
        c.setValContrato(rs.getLong("VAL_CONTRATO"));
    }
}
