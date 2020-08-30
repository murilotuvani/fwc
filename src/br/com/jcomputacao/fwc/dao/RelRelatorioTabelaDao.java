package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.RelRelatorioTabela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 30/06/2011 16:51:14
 * @author Murilo
 */
public class RelRelatorioTabelaDao extends Dao<RelRelatorioTabela> {
    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codElemento", Long.class, "COD_TABELA", java.sql.Types.BIGINT);
    }

    @Override
    public String getSelect() {
        return "SELECT R.COD_RELATORIO,R.COD_TABELA,R.NUM_ORDEM,R.DES_TITULO,R.DES_CONTEUDO,R.DES_ANALISE FROM REL_RELATORIO_TABELA R";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO REL_RELATORIO_TABELA (COD_RELATORIO,COD_TABELA,NUM_ORDEM,DES_TITULO,DES_CONTEUDO,DES_ANALISE) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE REL_RELATORIO_TABELA SET COD_RELATORIO=?,COD_TABELA=?,NUM_ORDEM=?,DES_TITULO=?,DES_CONTEUDO=?,DES_ANALISE=? WHERE COD_TABELA=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM REL_RELATORIO_TABELA WHERE COD_TABELA=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, RelRelatorioTabela r) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, r.getCodRelatorio());
        stmt.setLong(idx++, r.getCodElemento());
        stmt.setLong(idx++, r.getNumOrdem());
        stmt.setString(idx++, r.getTitulo());
        setNullSafe(stmt,r.getDesConteudo(), idx++);
        setNullSafe(stmt,r.getDesAnalise(), idx++);
    }

    @Override
    protected void setValues(RelRelatorioTabela r, ResultSet rs) throws SQLException {
        r.setCodRelatorio(rs.getLong("COD_RELATORIO"));
        r.setCodElemento(rs.getLong("COD_TABELA"));
        r.setNumOrdem(rs.getLong("NUM_ORDEM"));
        r.setTitulo(rs.getString("DES_TITULO"));
        r.setDesConteudo(rs.getString("DES_CONTEUDO"));
        r.setDesAnalise(rs.getString("DES_ANALISE"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected RelRelatorioTabela newInstance() {
        return new RelRelatorioTabela();
    }
    
    @Override
    protected void gerarChavePrimaria(RelRelatorioTabela grafico) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT REL_REL_TAB_SEQ.NEXTVAL FROM DUAL");
        long aLong = 0;
        if(rs.next()) {
            aLong = rs.getLong(1);
        }
        grafico.setCodElemento(aLong);
        rs.close();
        stmt.close();
        ConnectionFactory.devolver(conn);
    }
}
