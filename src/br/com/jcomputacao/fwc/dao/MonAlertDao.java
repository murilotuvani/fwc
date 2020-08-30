package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonAlert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonAlertDao {

    public String getSelect() {
        return "SELECT M.COD_BANCO_DADOS,M.DAT_COLETA,M.NUM_LINHA,M.VAL_ERRO FROM MON_ALERT M";
    }

    public String getInsert() {
        return "INSERT INTO MON_ALERT (COD_BANCO_DADOS,DAT_COLETA,NUM_LINHA,VAL_ERRO) VALUES (?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_ALERT SET COD_BANCO_DADOS=?,DAT_COLETA=?,NUM_LINHA=?,VAL_ERRO=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_ALERT WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonAlert m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setLong(idx++, m.getNumLinha());
        stmt.setString(idx++, m.getValErro());
    }

    protected void setValues(MonAlert m, ResultSet rs) throws SQLException {
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setDatColeta(rs.getDate("DAT_COLETA"));
        m.setNumLinha(rs.getLong("NUM_LINHA"));
        m.setValErro(rs.getString("VAL_ERRO"));
    }
}
