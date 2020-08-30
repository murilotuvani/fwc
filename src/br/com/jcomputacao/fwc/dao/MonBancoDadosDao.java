package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.MonBancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonBancoDadosDao extends Dao<MonBancoDados> {
    
    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codBancoDados", Long.class, "COD_BANCO_DADOS", java.sql.Types.BIGINT);
    }

    @Override
    public String getSelect() {
        return "SELECT M.COD_BANCO_DADOS,M.COD_CLIENTE,M.NUM_DBID,M.NOM_BANCO_DADOS,M.VAL_VERSAO,M.QTD_INSTANCES FROM MON_BANCO_DADOS M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_BANCO_DADOS (COD_BANCO_DADOS,COD_CLIENTE,NUM_DBID,NOM_BANCO_DADOS,VAL_VERSAO,QTD_INSTANCES) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_BANCO_DADOS SET COD_BANCO_DADOS=?,COD_CLIENTE=?,NUM_DBID=?,NOM_BANCO_DADOS=?,VAL_VERSAO=?,QTD_INSTANCES=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_BANCO_DADOS WHERE COD_BANCO_DADOS=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonBancoDados m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setLong(idx++, m.getCodCliente());
        stmt.setLong(idx++, m.getNumDbid());
        stmt.setString(idx++, m.getNomBancoDados());
        stmt.setString(idx++, m.getValVersao());
        stmt.setLong(idx++, m.getQtdInstances());
    }

    @Override
    protected void setValues(MonBancoDados m, ResultSet rs) throws SQLException {
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setCodCliente(rs.getLong("COD_CLIENTE"));
        m.setNumDbid(rs.getLong("NUM_DBID"));
        m.setNomBancoDados(rs.getString("NOM_BANCO_DADOS"));
        m.setValVersao(rs.getString("VAL_VERSAO"));
        m.setQtdInstances(rs.getLong("QTD_INSTANCES"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected MonBancoDados newInstance() {
        return new MonBancoDados();
    }
}
