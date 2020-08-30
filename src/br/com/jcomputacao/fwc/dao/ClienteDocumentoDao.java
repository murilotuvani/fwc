package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.ClienteDocumento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDocumentoDao {

    public String getSelect() {
        return "SELECT C.COD_DOCUMENTO,C.COD_CLIENTE,C.TIP_DOCUMENTO,C.NUM_DOCUMENTO,C.NUM_DIGITO FROM CLIENTE_DOCUMENTO C";
    }

    public String getInsert() {
        return "INSERT INTO CLIENTE_DOCUMENTO (COD_DOCUMENTO,COD_CLIENTE,TIP_DOCUMENTO,NUM_DOCUMENTO,NUM_DIGITO) VALUES (?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CLIENTE_DOCUMENTO SET COD_DOCUMENTO=?,COD_CLIENTE=?,TIP_DOCUMENTO=?,NUM_DOCUMENTO=?,NUM_DIGITO=?";
    }

    public String getDelete() {
        return "DELETE FROM CLIENTE_DOCUMENTO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, ClienteDocumento c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodDocumento());
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setString(idx++, c.getTipDocumento());
        stmt.setLong(idx++, c.getNumDocumento());
        stmt.setLong(idx++, c.getNumDigito());
    }

    protected void setValues(ClienteDocumento c, ResultSet rs) throws SQLException {
        c.setCodDocumento(rs.getLong("COD_DOCUMENTO"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setTipDocumento(rs.getString("TIP_DOCUMENTO"));
        c.setNumDocumento(rs.getLong("NUM_DOCUMENTO"));
        c.setNumDigito(rs.getLong("NUM_DIGITO"));
    }
}
