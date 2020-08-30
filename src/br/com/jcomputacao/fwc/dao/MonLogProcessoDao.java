package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonLogProcesso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonLogProcessoDao {

    public String getSelect() {
        return "SELECT M.COD_BANCO_DADOS,M.COD_SERVIDOR,M.NOM_PROCESSO,M.DAT_INICIO,M.DAT_FIM,M.DES_ERRO FROM MON_LOG_PROCESSO M";
    }

    public String getInsert() {
        return "INSERT INTO MON_LOG_PROCESSO (COD_BANCO_DADOS,COD_SERVIDOR,NOM_PROCESSO,DAT_INICIO,DAT_FIM,DES_ERRO) VALUES (?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_LOG_PROCESSO SET COD_BANCO_DADOS=?,COD_SERVIDOR=?,NOM_PROCESSO=?,DAT_INICIO=?,DAT_FIM=?,DES_ERRO=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_LOG_PROCESSO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonLogProcesso m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setString(idx++, m.getNomProcesso());
        stmt.setDate(idx++, new java.sql.Date(m.getDatInicio().getTime()));
        stmt.setDate(idx++, new java.sql.Date(m.getDatFim().getTime()));
        stmt.setString(idx++, m.getDesErro());
    }

    protected void setValues(MonLogProcesso m, ResultSet rs) throws SQLException {
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        m.setNomProcesso(rs.getString("NOM_PROCESSO"));
        m.setDatInicio(rs.getDate("DAT_INICIO"));
        m.setDatFim(rs.getDate("DAT_FIM"));
        m.setDesErro(rs.getString("DES_ERRO"));
    }
}
