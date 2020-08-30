package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonBancoDadosHg;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonBancoDadosHgDao {

    public String getSelect() {
        return "SELECT M.COD_BANCO_DADOS,M.COD_CLIENTE,M.NUM_DBID,M.NOM_BANCO_DADOS,M.VAL_VERSAO,M.QTD_INSTANCES FROM MON_BANCO_DADOS_HG M";
    }

    public String getInsert() {
        return "INSERT INTO MON_BANCO_DADOS_HG (COD_BANCO_DADOS,COD_CLIENTE,NUM_DBID,NOM_BANCO_DADOS,VAL_VERSAO,QTD_INSTANCES) VALUES (?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MON_BANCO_DADOS_HG SET COD_BANCO_DADOS=?,COD_CLIENTE=?,NUM_DBID=?,NOM_BANCO_DADOS=?,VAL_VERSAO=?,QTD_INSTANCES=?";
    }

    public String getDelete() {
        return "DELETE FROM MON_BANCO_DADOS_HG WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, MonBancoDadosHg m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setLong(idx++, m.getCodCliente());
        stmt.setLong(idx++, m.getNumDbid());
        stmt.setString(idx++, m.getNomBancoDados());
        stmt.setString(idx++, m.getValVersao());
        stmt.setLong(idx++, m.getQtdInstances());
    }

    protected void setValues(MonBancoDadosHg m, ResultSet rs) throws SQLException {
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setCodCliente(rs.getLong("COD_CLIENTE"));
        m.setNumDbid(rs.getLong("NUM_DBID"));
        m.setNomBancoDados(rs.getString("NOM_BANCO_DADOS"));
        m.setValVersao(rs.getString("VAL_VERSAO"));
        m.setQtdInstances(rs.getLong("QTD_INSTANCES"));
    }
}
