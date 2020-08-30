package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonSpam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonSpamDao {

    public String getSelect() {
        return "SELECT M.COD_MENSAGEM,M.NUM_LINHA,M.VAL_LINHA FROM MON_SPAM M";
    }

    public String getInsert() {
        return "INSERT INTO MON_SPAM (COD_MENSAGEM,NUM_LINHA,VAL_LINHA) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_SPAM SET COD_MENSAGEM=?,NUM_LINHA=?,VAL_LINHA=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_SPAM WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonSpam m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodMensagem());
        stmt.setLong(idx++, m.getNumLinha());
        stmt.setString(idx++, m.getValLinha());
    }

    protected void setValues(MonSpam m, ResultSet rs) throws SQLException {
        m.setCodMensagem(rs.getLong("COD_MENSAGEM"));
        m.setNumLinha(rs.getLong("NUM_LINHA"));
        m.setValLinha(rs.getString("VAL_LINHA"));
    }
}
