package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonDisponibilidadeBkp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonDisponibilidadeBkpDao {

    public String getSelect() {
        return "SELECT M.DAT_EVENTO,M.DAT_RECEBIMENTO,M.COD_SERVIDOR,M.COD_BANCO_DADOS,M.DES_EVENTO FROM MON_DISPONIBILIDADE_BKP M";
    }

    public String getInsert() {
        return "INSERT INTO MON_DISPONIBILIDADE_BKP (DAT_EVENTO,DAT_RECEBIMENTO,COD_SERVIDOR,COD_BANCO_DADOS,DES_EVENTO) VALUES (?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_DISPONIBILIDADE_BKP SET DAT_EVENTO=?,DAT_RECEBIMENTO=?,COD_SERVIDOR=?,COD_BANCO_DADOS=?,DES_EVENTO=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_DISPONIBILIDADE_BKP WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonDisponibilidadeBkp m) throws SQLException {
        int idx = 1;
        stmt.setDate(idx++, new java.sql.Date(m.getDatEvento().getTime()));
        stmt.setDate(idx++, new java.sql.Date(m.getDatRecebimento().getTime()));
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setString(idx++, m.getDesEvento());
    }

    protected void setValues(MonDisponibilidadeBkp m, ResultSet rs) throws SQLException {
        m.setDatEvento(rs.getDate("DAT_EVENTO"));
        m.setDatRecebimento(rs.getDate("DAT_RECEBIMENTO"));
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setDesEvento(rs.getString("DES_EVENTO"));
    }
}
