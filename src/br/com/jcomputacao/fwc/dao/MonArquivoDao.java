package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonArquivo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonArquivoDao {

    public String getSelect() {
        return "SELECT M.DAT_COLETA,M.COD_BANCO_DADOS,M.NOM_PONTO_MONTAGEM,M.TIP_ARQUIVO,M.NOM_ARQUIVO,M.NOM_TABLESPACE,M.VAL_UTILIZADO,M.VAL_LIVRE,M.VAL_MAXIMO,M.FLG_EXTENSIVEL,M.VAL_INCREMENTO FROM MON_ARQUIVO M";
    }

    public String getInsert() {
        return "INSERT INTO MON_ARQUIVO (DAT_COLETA,COD_BANCO_DADOS,NOM_PONTO_MONTAGEM,TIP_ARQUIVO,NOM_ARQUIVO,NOM_TABLESPACE,VAL_UTILIZADO,VAL_LIVRE,VAL_MAXIMO,FLG_EXTENSIVEL,VAL_INCREMENTO) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_ARQUIVO SET DAT_COLETA=?,COD_BANCO_DADOS=?,NOM_PONTO_MONTAGEM=?,TIP_ARQUIVO=?,NOM_ARQUIVO=?,NOM_TABLESPACE=?,VAL_UTILIZADO=?,VAL_LIVRE=?,VAL_MAXIMO=?,FLG_EXTENSIVEL=?,VAL_INCREMENTO=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_ARQUIVO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonArquivo m) throws SQLException {
        int idx = 1;
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setString(idx++, m.getNomPontoMontagem());
        stmt.setString(idx++, m.getTipArquivo());
        stmt.setString(idx++, m.getNomArquivo());
        stmt.setString(idx++, m.getNomTablespace());
        stmt.setLong(idx++, m.getValUtilizado());
        stmt.setLong(idx++, m.getValLivre());
        stmt.setLong(idx++, m.getValMaximo());
        stmt.setString(idx++, m.getFlgExtensivel());
        stmt.setLong(idx++, m.getValIncremento());
    }

    protected void setValues(MonArquivo m, ResultSet rs) throws SQLException {
        m.setDatColeta(rs.getDate("DAT_COLETA"));
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setNomPontoMontagem(rs.getString("NOM_PONTO_MONTAGEM"));
        m.setTipArquivo(rs.getString("TIP_ARQUIVO"));
        m.setNomArquivo(rs.getString("NOM_ARQUIVO"));
        m.setNomTablespace(rs.getString("NOM_TABLESPACE"));
        m.setValUtilizado(rs.getLong("VAL_UTILIZADO"));
        m.setValLivre(rs.getLong("VAL_LIVRE"));
        m.setValMaximo(rs.getLong("VAL_MAXIMO"));
        m.setFlgExtensivel(rs.getString("FLG_EXTENSIVEL"));
        m.setValIncremento(rs.getLong("VAL_INCREMENTO"));
    }
}
