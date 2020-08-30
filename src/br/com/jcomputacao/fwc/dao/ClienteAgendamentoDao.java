package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.ClienteAgendamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteAgendamentoDao {

    public String getSelect() {
        return "SELECT C.COD_AGENDAMENTO,C.COD_CLIENTE,C.COD_OCORRENCIA,C.NOM_USUARIO,C.TIP_AGENDAMENTO,C.FLG_REALIZADO FROM CLIENTE_AGENDAMENTO C";
    }

    public String getInsert() {
        return "INSERT INTO CLIENTE_AGENDAMENTO (COD_AGENDAMENTO,COD_CLIENTE,COD_OCORRENCIA,NOM_USUARIO,TIP_AGENDAMENTO,FLG_REALIZADO) VALUES (?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CLIENTE_AGENDAMENTO SET COD_AGENDAMENTO=?,COD_CLIENTE=?,COD_OCORRENCIA=?,NOM_USUARIO=?,TIP_AGENDAMENTO=?,FLG_REALIZADO=?";
    }

    public String getDelete() {
        return "DELETE FROM CLIENTE_AGENDAMENTO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, ClienteAgendamento c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodAgendamento());
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setLong(idx++, c.getCodOcorrencia());
        stmt.setString(idx++, c.getNomUsuario());
        stmt.setString(idx++, c.getTipAgendamento());
        stmt.setString(idx++, c.getFlgRealizado());
    }

    protected void setValues(ClienteAgendamento c, ResultSet rs) throws SQLException {
        c.setCodAgendamento(rs.getLong("COD_AGENDAMENTO"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setCodOcorrencia(rs.getLong("COD_OCORRENCIA"));
        c.setNomUsuario(rs.getString("NOM_USUARIO"));
        c.setTipAgendamento(rs.getString("TIP_AGENDAMENTO"));
        c.setFlgRealizado(rs.getString("FLG_REALIZADO"));
    }
}
