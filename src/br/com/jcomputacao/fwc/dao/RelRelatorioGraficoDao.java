package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.RelRelatorioGrafico;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RelRelatorioGraficoDao extends Dao<RelRelatorioGrafico> {
    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codElemento", Long.class, "COD_GRAFICO", java.sql.Types.BIGINT);
    }

    @Override
    public String getSelect() {
        return "SELECT R.COD_RELATORIO,R.COD_GRAFICO,R.NUM_ORDEM,R.DES_TITULO,R.BLO_CONTEUDO,R.DES_ANALISE FROM REL_RELATORIO_GRAFICO R";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO REL_RELATORIO_GRAFICO (COD_RELATORIO,COD_GRAFICO,NUM_ORDEM,DES_TITULO,BLO_CONTEUDO,DES_ANALISE) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE REL_RELATORIO_GRAFICO SET COD_RELATORIO=?,COD_GRAFICO=?,NUM_ORDEM=?,DES_TITULO=?,BLO_CONTEUDO=?,DES_ANALISE=? WHERE COD_GRAFICO=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM REL_RELATORIO_GRAFICO WHERE COD_GRAFICO=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, RelRelatorioGrafico r) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, r.getCodRelatorio());
        stmt.setLong(idx++, r.getCodElemento());
        stmt.setLong(idx++, r.getNumOrdem());
        stmt.setString(idx++, r.getTitulo());
        stmt.setBlob(idx++, new ByteArrayInputStream(r.getBloConteudo()));
        setNullSafe(stmt,r.getDesAnalise(), idx++);
    }

    @Override
    protected void setValues(RelRelatorioGrafico r, ResultSet rs) throws SQLException {
        r.setCodRelatorio(rs.getLong("COD_RELATORIO"));
        r.setCodElemento(rs.getLong("COD_GRAFICO"));
        r.setNumOrdem(rs.getLong("NUM_ORDEM"));
        r.setTitulo(rs.getString("DES_TITULO"));
        Blob blob = rs.getBlob("BLO_CONTEUDO");
        long length = blob.length();
        r.setBloConteudo(blob.getBytes(1, (int) length));
        r.setDesAnalise(rs.getString("DES_ANALISE"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected RelRelatorioGrafico newInstance() {
        return new RelRelatorioGrafico();
    }
    
    @Override
    protected void gerarChavePrimaria(RelRelatorioGrafico grafico) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT REL_REL_GRA_SEQ.NEXTVAL FROM DUAL");
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