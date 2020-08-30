package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.chart.GraficoTipo;
import br.com.jcomputacao.fwc.model.Configuracao;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 29/07/2011 00:07:15
 * @author Murilo
 */
public class RelConfiguacaoDao extends Dao<RelConfiguracao> {
    
    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codigo", Long.class, "COD_CONFIGURACAO", java.sql.Types.BIGINT);
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

     @Override
    public String getSelect() {
        return "Select C.COD_CONFIGURACAO,C.DES_TIPO,C.DES_TITULO,C.BOL_USA_GRID,C.COR_PLOTAGEN,C.COR_DE_FUNDO,C.BOL_USA_BORDA FROM REL_CONFIGURACAO C";
    }

    @Override
    public String getInsert() {
        return " INSERT INTO REL_CONFIGURACAO (COD_CONFIGURACAO,DES_TIPO,DES_TITULO,BOL_USA_GRID,COR_PLOTAGEN,COR_DE_FUNDO,BOL_USA_BORDA) values(?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE REL_CONFIGURACAO SET DES_TITULO=?,BOL_USA_GRID=?,COR_PLOTAGEN=?,COR_DE_FUNDO=?,BOL_USA_BORDA=? WHERE COD_CONFIGURACAO =?";// WHERE COD_CONFIGURACAO =?
    }

    @Override
    public String getDelete() {
        return " DELETE FROM REL_CONFIGURACAO WHERE ID=?";
    }

    @Override
    protected RelConfiguracao newInstance() {
        return new RelConfiguracao();
    }

 
    @Override
    protected void prepareUpdate(PreparedStatement stmt, RelConfiguracao t) throws SQLException, DaoException {
        int i = 1;
//        stmt.setInt(i++, t.getCodigo());
//        stmt.setString(i++,String.valueOf( t.getTipo().ordinal()));
        stmt.setString(i++, t.getTitulo());
//        stmt.setInt(i++,t.getUsaGrid().compareTo(Boolean.FALSE));        
        stmt.setInt(i++,t.getUsaGridInt());
        stmt.setInt(i++, t.getCorPlot());
        stmt.setInt(i++, t.getCorFundo());
//        stmt.setInt(i++,t.getExibeBorda().compareTo(Boolean.FALSE));
        stmt.setInt(i++,t.getExibeBordaInt());      
        
    }

    @Override
    protected void setValues(RelConfiguracao t, ResultSet rs) throws SQLException, DaoException {
        t.setCodigo(rs.getInt("COD_CONFIGURACAO"));
        t.setTipo(GraficoTipo.valueOf(rs.getString("DES_TIPO")));
        t.setTitulo(rs.getString("DES_TITULO"));
        t.setUsaGrid(rs.getBoolean("BOL_USA_GRID"));
        t.setCorPlot(rs.getInt("COR_PLOTAGEN"));
        t.setCorFundo(rs.getInt("COR_DE_FUNDO"));
        t.setExibeBorda(rs.getBoolean("BOL_USA_BORDA"));

    }
  
    protected void gerarChavePrimaria(Configuracao configuracoes) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT REL_CONFIGURACAO_SEQ.NEXTVAL FROM REL_CONFIGURACAO");
        int aInt = 0;
        if (rs.next()) {
            aInt = rs.getInt(1);
        }
        configuracoes.setCodConfiguracao(aInt);
        rs.close();
        stmt.close();
        ConnectionFactory.devolver(conn);
    }

    public RelConfiguracao buscar(String tipo) throws DaoException {
        String comando = getSelect() + " WHERE DES_TIPO=?";

        Connection conn = null;
        RelConfiguracao t = null;
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(comando);
            stmt.setString(1, tipo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                t = newInstance();
                setValues(t, rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar recuperar o cadastro", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return t;

    }
    
}
