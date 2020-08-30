/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.model.RelConfiguracaoSerie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Felipe Caue 19/10/2011 09:55:41
 */
public class RelConfiguracaoSerieDao extends Dao<RelConfiguracaoSerie> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codigoConfiguracaoSerie", Long.class, "COD_CONFIGURACAOCOD_CONFIGURACAO_SERIE", java.sql.Types.BIGINT);
    }

    public String getSelect() {
        return "Select C.NUM_SERIE,C.COD_CONFIGURACAO_SERIE,C.COD_CONFIGURACAO,C.COR_SERIE FROM REL_CONFIGURACAO_SERIE C";
    }

    @Override
    public String getUpdate() {
        return "UPDATE REL_CONFIGURACAO_SERIE SET NUM_SERIE=?,COD_CONFIGURACAO_SERIE=?,COD_CONFIGURACAO=?,COR_SERIE=?";// WHERE COD_CONFIGURACAO =?
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override

       protected RelConfiguracaoSerie newInstance() {
        return new RelConfiguracaoSerie();
    }

    @Override
    protected String getInsert() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String getDelete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, RelConfiguracaoSerie t) throws SQLException, DaoException {
        int i = 1;

        stmt.setInt(i++, t.getCodigoConfiguracao());
        stmt.setInt(i++, t.getCodigoConfiguracaoSerie());
        stmt.setInt(i++, t.getNumeroSerie());
        stmt.setInt(i++, t.getCorDaSerie());
    }

    @Override
    protected void setValues(RelConfiguracaoSerie t, ResultSet rs) throws SQLException, DaoException {
        t.setNumeroSerie(rs.getInt("NUM_SERIE"));
        t.setCodigoConfiguracaoSerie(rs.getInt("COD_CONFIGURACAO_SERIE"));
        t.setCodigoConfiguracao(rs.getInt("COD_CONFIGURACAO_SERIE"));
        t.setCorDaSerie(rs.getInt("COR_SERIE"));
    }
    @Override
        protected void gerarChavePrimaria(RelConfiguracaoSerie configuracoes) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT REL_CONFIGURACAO_SERIE_SEQ.NEXTVAL FROM REL_CONFIGURACAO_SERIE");
        int aInt = 0;
        if (rs.next()) {
            aInt = rs.getInt(1);
        }
        configuracoes.setCodigoConfiguracao(aInt);
        rs.close();
        stmt.close();
        ConnectionFactory.devolver(conn);
    }
         public RelConfiguracaoSerie buscar(String tipoSerie) throws DaoException {
        String comando = getSelect() + " WHERE COD_CONFIGURACAO_SERIE =?";

        Connection conn = null;
        RelConfiguracaoSerie t = null;
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(comando);
            stmt.setString(1, tipoSerie);

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
