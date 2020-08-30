package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.ClienteOcorrencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteOcorrenciaDao {

    public String getSelect() {
        return "SELECT C.COD_OCORRENCIA,C.COD_CLIENTE,C.NOM_USUARIO,C.DAT_OCORRENCIA,C.VAL_OBS,C.COD_OCORRENCIA_PAI FROM CLIENTE_OCORRENCIA C";
    }

    public String getInsert() {
        return "INSERT INTO CLIENTE_OCORRENCIA (COD_OCORRENCIA,COD_CLIENTE,NOM_USUARIO,DAT_OCORRENCIA,VAL_OBS,COD_OCORRENCIA_PAI) VALUES (?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CLIENTE_OCORRENCIA SET COD_OCORRENCIA=?,COD_CLIENTE=?,NOM_USUARIO=?,DAT_OCORRENCIA=?,VAL_OBS=?,COD_OCORRENCIA_PAI=?";
    }

    public String getDelete() {
        return "DELETE FROM CLIENTE_OCORRENCIA WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, ClienteOcorrencia c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodOcorrencia());
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setString(idx++, c.getNomUsuario());
        stmt.setDate(idx++, new java.sql.Date(c.getDatOcorrencia().getTime()));
        stmt.setString(idx++, c.getValObs());
        stmt.setLong(idx++, c.getCodOcorrenciaPai());
    }

    protected void setValues(ClienteOcorrencia c, ResultSet rs) throws SQLException {
        c.setCodOcorrencia(rs.getLong("COD_OCORRENCIA"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setNomUsuario(rs.getString("NOM_USUARIO"));
        c.setDatOcorrencia(rs.getDate("DAT_OCORRENCIA"));
        c.setValObs(rs.getString("VAL_OBS"));
        c.setCodOcorrenciaPai(rs.getLong("COD_OCORRENCIA_PAI"));
    }
}
