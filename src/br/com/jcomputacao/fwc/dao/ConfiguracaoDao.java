package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.model.Configuracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Felipe Caue 30/08/2011 11:50:42
 */
public class ConfiguracaoDao extends Dao<Configuracao> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codConfiguracao", Integer.class, "COD_CONFIGURACAO", java.sql.Types.INTEGER);
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
        return "UPDATE REL_CONFIGURACAO SET  DES_TITULO=?,BOL_USA_GRID=?,COR_PLOTAGEN=?,COR_DE_FUNDO=?,BOL_USA_BORDA=? WHERE COD_CONFIGURACAO =?";
    }

    @Override
    public String getDelete() {
        return " DELETE FROM REL_CONFIGURACAO WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, Configuracao c) throws SQLException {
        int i = 1;
//        stmt.setString(i++, c.getDescricaoTipo());
        stmt.setString(i++, c.getDescricaoTitulo());
        stmt.setInt(i++, c.getBolUsaGgrid());
        stmt.setInt(i++, c.getAreaDePlotagem());
        stmt.setInt(i++, c.getCorDeFundo());
        stmt.setInt(i++, c.getBolUsaBorda());
        stmt.setInt(i++, c.getCodConfiguracao());
    }

    @Override
    protected void setValues(Configuracao c, ResultSet rs) throws SQLException {
        c.setCodConfiguracao(rs.getInt("COD_CONFIGURACAO"));
        c.setDescricaoTipo(rs.getString("DES_TIPO"));
        c.setDescricaoTitulo(rs.getString("DES_TITULO"));
        c.setBolUsaGgrid(rs.getInt("BOL_USA_GRID"));
        c.setAreaDePlotagem(rs.getInt("COR_PLOTAGEN"));
        c.setCorDeFundo(rs.getInt("COR_DE_FUNDO"));
        c.setBolUsaBorda(rs.getInt("BOL_USA_BORDA"));

    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected Configuracao newInstance() {
        return new Configuracao();
    }

    @Override
    protected void gerarChavePrimaria(Configuracao configuracoes) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
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

    public Configuracao buscar(String tipo) throws DaoException {
        String comando = getSelect() + " WHERE DES_TIPO=?";

        Connection conn = null;
        Configuracao t = null;
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
