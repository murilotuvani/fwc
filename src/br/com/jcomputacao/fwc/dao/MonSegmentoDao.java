package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonSegmento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonSegmentoDao {

    public String getSelect() {
        return "SELECT M.DAT_COLETA,M.COD_BANCO_DADOS,M.NOM_OWNER,M.TIP_SEGMENTO,M.NOM_TABLESPACE,M.VAL_BYTES FROM MON_SEGMENTO M";
    }

    public String getInsert() {
        return "INSERT INTO MON_SEGMENTO (DAT_COLETA,COD_BANCO_DADOS,NOM_OWNER,TIP_SEGMENTO,NOM_TABLESPACE,VAL_BYTES) VALUES (?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_SEGMENTO SET DAT_COLETA=?,COD_BANCO_DADOS=?,NOM_OWNER=?,TIP_SEGMENTO=?,NOM_TABLESPACE=?,VAL_BYTES=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_SEGMENTO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonSegmento m) throws SQLException {
        int idx = 1;
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setString(idx++, m.getNomOwner());
        stmt.setString(idx++, m.getTipSegmento());
        stmt.setString(idx++, m.getNomTablespace());
        stmt.setLong(idx++, m.getValBytes());
    }

    protected void setValues(MonSegmento m, ResultSet rs) throws SQLException {
        m.setDatColeta(rs.getDate("DAT_COLETA"));
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setNomOwner(rs.getString("NOM_OWNER"));
        m.setTipSegmento(rs.getString("TIP_SEGMENTO"));
        m.setNomTablespace(rs.getString("NOM_TABLESPACE"));
        m.setValBytes(rs.getLong("VAL_BYTES"));
    }
}
