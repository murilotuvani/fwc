package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.MonBancoDadosServidor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonBancoDadosServidorDao extends Dao<MonBancoDadosServidor> {

    @Override
    public String getSelect() {
        return "SELECT M.COD_BANCO_DADOS,M.COD_SERVIDOR,M.INSTANCE_NUMBER FROM MON_BANCO_DADOS_SERVIDOR M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_BANCO_DADOS_SERVIDOR (COD_BANCO_DADOS,COD_SERVIDOR,INSTANCE_NUMBER) VALUES (?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_BANCO_DADOS_SERVIDOR SET COD_BANCO_DADOS=?,COD_SERVIDOR=?,INSTANCE_NUMBER=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_BANCO_DADOS_SERVIDOR WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonBancoDadosServidor m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setLong(idx++, m.getInstanceNumber());
    }

    @Override
    protected void setValues(MonBancoDadosServidor m, ResultSet rs) throws SQLException {
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        m.setInstanceNumber(rs.getLong("INSTANCE_NUMBER"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected MonBancoDadosServidor newInstance() {
        return new MonBancoDadosServidor();
    }
}
