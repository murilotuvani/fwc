package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonBackup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonBackupDao {

    public String getSelect() {
        return "SELECT M.COD_BACKUP,M.DAT_COLETA,M.COD_BANCO_DADOS,M.TIP_BACKUP,M.DAT_INICIO,M.DAT_FIM,M.VAL_BYTES_ENTRADA,M.VAL_BYTES_SAIDA,M.COD_RETORNO FROM MON_BACKUP M";
    }

    public String getInsert() {
        return "INSERT INTO MON_BACKUP (COD_BACKUP,DAT_COLETA,COD_BANCO_DADOS,TIP_BACKUP,DAT_INICIO,DAT_FIM,VAL_BYTES_ENTRADA,VAL_BYTES_SAIDA,COD_RETORNO) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_BACKUP SET COD_BACKUP=?,DAT_COLETA=?,COD_BANCO_DADOS=?,TIP_BACKUP=?,DAT_INICIO=?,DAT_FIM=?,VAL_BYTES_ENTRADA=?,VAL_BYTES_SAIDA=?,COD_RETORNO=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_BACKUP WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonBackup m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBackup());
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setString(idx++, m.getTipBackup());
        stmt.setDate(idx++, new java.sql.Date(m.getDatInicio().getTime()));
        stmt.setDate(idx++, new java.sql.Date(m.getDatFim().getTime()));
        stmt.setLong(idx++, m.getValBytesEntrada());
        stmt.setLong(idx++, m.getValBytesSaida());
        stmt.setLong(idx++, m.getCodRetorno());
    }

    protected void setValues(MonBackup m, ResultSet rs) throws SQLException {
        m.setCodBackup(rs.getLong("COD_BACKUP"));
        m.setDatColeta(rs.getDate("DAT_COLETA"));
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setTipBackup(rs.getString("TIP_BACKUP"));
        m.setDatInicio(rs.getDate("DAT_INICIO"));
        m.setDatFim(rs.getDate("DAT_FIM"));
        m.setValBytesEntrada(rs.getLong("VAL_BYTES_ENTRADA"));
        m.setValBytesSaida(rs.getLong("VAL_BYTES_SAIDA"));
        m.setCodRetorno(rs.getLong("COD_RETORNO"));
    }
}
