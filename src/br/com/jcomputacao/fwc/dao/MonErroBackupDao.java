package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonErroBackup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonErroBackupDao {

    public String getSelect() {
        return "SELECT M.COD_BACKUP,M.NUM_LINHA,M.VAL_LINHA FROM MON_ERRO_BACKUP M";
    }

    public String getInsert() {
        return "INSERT INTO MON_ERRO_BACKUP (COD_BACKUP,NUM_LINHA,VAL_LINHA) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_ERRO_BACKUP SET COD_BACKUP=?,NUM_LINHA=?,VAL_LINHA=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_ERRO_BACKUP WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonErroBackup m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBackup());
        stmt.setLong(idx++, m.getNumLinha());
        stmt.setString(idx++, m.getValLinha());
    }

    protected void setValues(MonErroBackup m, ResultSet rs) throws SQLException {
        m.setCodBackup(rs.getLong("COD_BACKUP"));
        m.setNumLinha(rs.getLong("NUM_LINHA"));
        m.setValLinha(rs.getString("VAL_LINHA"));
    }
}
