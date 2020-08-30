package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.model.MonDisco;
import br.com.jcomputacao.util.TimeUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonDiscoDao extends Dao<MonDisco> {
    
    @Override
    public String getSelect() {
        return "SELECT M.COD_SERVIDOR,TO_CHAR(M.DAT_COLETA,'YYYY-MM-DD HH24:MI:SS') DAT_COLETA,\n"
                + "M.NOM_PONTO_MONTAGEM,M.VAL_TAMANHO,M.VAL_UTILIZADO,M.VAL_DISPONIVEL,M.FLG_AUTOEXTEND\n"
                + "FROM MON_DISCO M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_DISCO (COD_SERVIDOR,DAT_COLETA,NOM_PONTO_MONTAGEM,VAL_TAMANHO,VAL_UTILIZADO,VAL_DISPONIVEL,FLG_AUTOEXTEND) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_DISCO SET COD_SERVIDOR=?,DAT_COLETA=?,NOM_PONTO_MONTAGEM=?,VAL_TAMANHO=?,VAL_UTILIZADO=?,VAL_DISPONIVEL=?,FLG_AUTOEXTEND=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_DISCO WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonDisco m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setString(idx++, m.getNomPontoMontagem());
        stmt.setLong(idx++, m.getValTamanho());
        stmt.setLong(idx++, m.getValUtilizado());
        stmt.setLong(idx++, m.getValDisponivel());
        stmt.setString(idx++, m.getFlgAutoextend());
    }

    @Override
    protected void setValues(MonDisco m, ResultSet rs) throws SQLException {
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        String dtColeta = rs.getString("DAT_COLETA");
        m.setDatColeta(TimeUtil.getDateTimeFromDatabase(dtColeta));
        m.setNomPontoMontagem(rs.getString("NOM_PONTO_MONTAGEM"));
        m.setValTamanho(rs.getLong("VAL_TAMANHO"));
        m.setValUtilizado(rs.getLong("VAL_UTILIZADO"));
        m.setValDisponivel(rs.getLong("VAL_DISPONIVEL"));
        m.setFlgAutoextend(rs.getString("FLG_AUTOEXTEND"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected MonDisco newInstance() {
        return new MonDisco();
    }

    public List<MonDisco> listarMediaDiaria(String where) throws DaoException {
        String comando = "SELECT M.COD_SERVIDOR"
                + "\n,TO_DATE(M.DAT_COLETA, 'YYYY-MM-DD') DAT_COLETA"
                + "\n,M.NOM_PONTO_MONTAGEM,AVG(M.VAL_TAMANHO) VAL_TAMANHO"
                + "\n,AVG(M.VAL_UTILIZADO) VAL_UTILIZADO"
                + "\n,AVG(M.VAL_DISPONIVEL) VAL_DISPONIVEL"
                + "\n,1 as FLG_AUTOEXTEND"
                + "\nFROM MON_DISCO M\n"
                + where
                + "\nGROUP BY COD_SERVIDOR,NOM_PONTO_MONTAGEM,TO_DATE(M.DAT_COLETA, 'YYYY-MM-DD')"
                + "\nORDER BY COD_SERVIDOR,NOM_PONTO_MONTAGEM,TO_DATE(M.DAT_COLETA, 'YYYY-MM-DD')";

        List<MonDisco> lista = new ArrayList<MonDisco>();
        java.sql.Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            System.err.println(comando);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINER, comando);
            ResultSet rs = stmt.executeQuery(comando);

            while (rs.next()) {
                MonDisco entidade = newInstance();
                setValues(entidade, rs);
                lista.add(entidade);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tetnar ler o(s) registro(s)", ex);
        } finally {
            if(conn!=null) {
                ConnectionFactory.devolver(conn);
            }
        }
        return lista;

    }
}
