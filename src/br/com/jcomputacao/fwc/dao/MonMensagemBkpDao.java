package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonMensagemBkp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonMensagemBkpDao {

    public String getSelect() {
        return "SELECT M.COD_MENSAGEM,M.DAT_RECEBIMENTO,M.NUM_LINHA,M.COD_CLIENTE,M.NOM_HOST,M.NUM_DBID,M.TIP_MENSAGEM,M.DAT_ENVIO,M.VAL_LINHA FROM MON_MENSAGEM_BKP M";
    }

    public String getInsert() {
        return "INSERT INTO MON_MENSAGEM_BKP (COD_MENSAGEM,DAT_RECEBIMENTO,NUM_LINHA,COD_CLIENTE,NOM_HOST,NUM_DBID,TIP_MENSAGEM,DAT_ENVIO,VAL_LINHA) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_MENSAGEM_BKP SET COD_MENSAGEM=?,DAT_RECEBIMENTO=?,NUM_LINHA=?,COD_CLIENTE=?,NOM_HOST=?,NUM_DBID=?,TIP_MENSAGEM=?,DAT_ENVIO=?,VAL_LINHA=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_MENSAGEM_BKP WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonMensagemBkp m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodMensagem());
        stmt.setDate(idx++, new java.sql.Date(m.getDatRecebimento().getTime()));
        stmt.setLong(idx++, m.getNumLinha());
        stmt.setLong(idx++, m.getCodCliente());
        stmt.setString(idx++, m.getNomHost());
        stmt.setLong(idx++, m.getNumDbid());
        stmt.setLong(idx++, m.getTipMensagem());
        stmt.setDate(idx++, new java.sql.Date(m.getDatEnvio().getTime()));
        stmt.setString(idx++, m.getValLinha());
    }

    protected void setValues(MonMensagemBkp m, ResultSet rs) throws SQLException {
        m.setCodMensagem(rs.getLong("COD_MENSAGEM"));
        m.setDatRecebimento(rs.getDate("DAT_RECEBIMENTO"));
        m.setNumLinha(rs.getLong("NUM_LINHA"));
        m.setCodCliente(rs.getLong("COD_CLIENTE"));
        m.setNomHost(rs.getString("NOM_HOST"));
        m.setNumDbid(rs.getLong("NUM_DBID"));
        m.setTipMensagem(rs.getLong("TIP_MENSAGEM"));
        m.setDatEnvio(rs.getDate("DAT_ENVIO"));
        m.setValLinha(rs.getString("VAL_LINHA"));
    }
}
