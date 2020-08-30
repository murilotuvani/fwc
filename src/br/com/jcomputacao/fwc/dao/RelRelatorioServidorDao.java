package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.RelRelatorioServidor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelRelatorioServidorDao extends Dao<RelRelatorioServidor> {
    
    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.setAutogerada(false);
    }

    @Override
    public String getSelect() {
        return "SELECT R.COD_RELATORIO,R.COD_SERVIDOR FROM REL_RELATORIO_SERVIDOR R";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO REL_RELATORIO_SERVIDOR (COD_RELATORIO,COD_SERVIDOR) VALUES (?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE REL_RELATORIO_SERVIDOR SET COD_RELATORIO=?,COD_SERVIDOR=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM REL_RELATORIO_SERVIDOR WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, RelRelatorioServidor r) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, r.getCodRelatorio());
        stmt.setLong(idx++, r.getCodServidor());
    }

    @Override
    protected void setValues(RelRelatorioServidor r, ResultSet rs) throws SQLException {
        r.setCodRelatorio(rs.getLong("COD_RELATORIO"));
        r.setCodServidor(rs.getLong("COD_SERVIDOR"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected RelRelatorioServidor newInstance() {
        return new RelRelatorioServidor();
    }
}