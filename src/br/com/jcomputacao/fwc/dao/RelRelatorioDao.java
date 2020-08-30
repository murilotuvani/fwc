package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RelRelatorioDao extends Dao<RelRelatorio> {
    
    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codRelatorio", Long.class, "COD_RELATORIO", java.sql.Types.BIGINT);
    }

    @Override
    public String getSelect() {
        return "SELECT R.COD_RELATORIO,R.COD_CLIENTE,R.NOM_USUARIO,R.DAT_CADASTRO,R.DAT_INICIO,R.DAT_FIM\n"
                + ",C.NOM_CLIENTE\n"
                + "FROM REL_RELATORIO R LEFT JOIN CLIENTE C ON R.COD_CLIENTE=C.COD_CLIENTE";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO REL_RELATORIO (COD_RELATORIO,COD_CLIENTE,NOM_USUARIO,DAT_CADASTRO,DAT_INICIO,DAT_FIM) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE REL_RELATORIO SET COD_RELATORIO=?,COD_CLIENTE=?,NOM_USUARIO=?,DAT_CADASTRO=?,DAT_INICIO=?,DAT_FIM=? WHERE COD_RELATORIO=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM REL_RELATORIO WHERE COD_RELATORIO=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, RelRelatorio r) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, r.getCodRelatorio());
        stmt.setLong(idx++, r.getCodCliente());
        stmt.setString(idx++, r.getNomUsuario());
        stmt.setTimestamp(idx++, new java.sql.Timestamp(r.getDatCadastro().getTime()));
        stmt.setDate(idx++, new java.sql.Date(r.getDatInicio().getTime()));
        stmt.setDate(idx++, new java.sql.Date(r.getDatFim().getTime()));
    }

    @Override
    protected void setValues(RelRelatorio r, ResultSet rs) throws SQLException {
        r.setCodRelatorio(rs.getLong("COD_RELATORIO"));
        r.setCodCliente(rs.getLong("COD_CLIENTE"));
        r.setNomUsuario(rs.getString("NOM_USUARIO"));
        r.setDatCadastro(rs.getTimestamp("DAT_CADASTRO"));
        r.setDatInicio(rs.getDate("DAT_INICIO"));
        r.setDatFim(rs.getDate("DAT_FIM"));
        r.setNomCliente(rs.getString("NOM_CLIENTE"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected RelRelatorio newInstance() {
        return new RelRelatorio();
    }
    
    @Override
    protected void gerarChavePrimaria(RelRelatorio relatorio) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT REL_REL_SEQ.NEXTVAL FROM DUAL");
        long aLong = 0;
        if(rs.next()) {
            aLong = rs.getLong(1);
        }
        relatorio.setCodRelatorio(aLong);
        rs.close();
        stmt.close();
        ConnectionFactory.devolver(conn);
    }
    
    public void delete(Long codigo) throws DaoException {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.addBatch("DELETE FROM REL_RELATORIO_SERVIDOR WHERE COD_RELATORIO=" + codigo);
            stmt.addBatch("DELETE FROM REL_RELATORIO_GRAFICO WHERE COD_RELATORIO=" + codigo);
            stmt.addBatch("DELETE FROM REL_RELATORIO_TABELA WHERE COD_RELATORIO=" + codigo);
            stmt.addBatch("DELETE FROM REL_RELATORIO WHERE COD_RELATORIO=" + codigo);
            stmt.executeBatch();
            conn.commit();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tetnar ler o(s) registro(s)", ex);
        } finally {
            if (conn != null) {
                ConnectionFactory.devolver(conn);
            }
        }
        
    }
}