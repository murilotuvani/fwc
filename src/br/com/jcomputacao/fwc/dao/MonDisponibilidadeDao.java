package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.MonDisponibilidade;
import br.com.jcomputacao.util.TimeUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonDisponibilidadeDao extends Dao<MonDisponibilidade> {

    @Override
    public String getSelect() {
        return "SELECT TO_CHAR(M.DAT_EVENTO,'YYYY-MM-DD HH24:MI:SS') DAT_EVENTO\n"
                + ",TO_CHAR(M.DAT_RECEBIMENTO,'YYYY-MM-DD HH24:MI:SS') DAT_RECEBIMENTO\n"
                + ",M.COD_SERVIDOR,M.COD_BANCO_DADOS,M.DES_EVENTO,M.INSTANCE_NUMBER\n"
                + "FROM MON_DISPONIBILIDADE M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_DISPONIBILIDADE (DAT_EVENTO,DAT_RECEBIMENTO,COD_SERVIDOR,COD_BANCO_DADOS,DES_EVENTO,INSTANCE_NUMBER) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_DISPONIBILIDADE SET DAT_EVENTO=?,DAT_RECEBIMENTO=?,COD_SERVIDOR=?,COD_BANCO_DADOS=?,DES_EVENTO=?,INSTANCE_NUMBER=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_DISPONIBILIDADE WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonDisponibilidade m) throws SQLException {
        int idx = 1;
        stmt.setDate(idx++, new java.sql.Date(m.getDatEvento().getTime()));
        stmt.setDate(idx++, new java.sql.Date(m.getDatRecebimento().getTime()));
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setString(idx++, m.getDesEvento());
        stmt.setLong(idx++, m.getInstanceNumber());
    }

    @Override
    protected void setValues(MonDisponibilidade m, ResultSet rs) throws SQLException {
        String data = rs.getString("DAT_EVENTO");
        m.setDatEvento(TimeUtil.getDateTimeFromDatabase(data));
        
        data = rs.getString("DAT_RECEBIMENTO");
        m.setDatRecebimento(TimeUtil.getDateTimeFromDatabase(data));
        
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        m.setDesEvento(rs.getString("DES_EVENTO"));
        m.setInstanceNumber(rs.getLong("INSTANCE_NUMBER"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected MonDisponibilidade newInstance() {
        return new MonDisponibilidade();
    }
}
