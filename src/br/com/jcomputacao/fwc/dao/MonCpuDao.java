package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.MonCpu;
import br.com.jcomputacao.util.TimeUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonCpuDao extends Dao<MonCpu> {

    @Override
    public String getSelect() {
        return "SELECT M.COD_SERVIDOR,TO_CHAR(M.DAT_COLETA,'YYYY-MM-DD HH24:MI:SS') DAT_COLETA,\n"
                + "M.PCT_USER,M.PCT_NICE,M.PCT_SYSTEM,M.PCT_IOWAIT,M.PCT_STEAL,M.PCT_IDLE FROM MON_CPU M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_CPU (COD_SERVIDOR,DAT_COLETA,PCT_USER,PCT_NICE,PCT_SYSTEM,PCT_IOWAIT,PCT_STEAL,PCT_IDLE) VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_CPU SET COD_SERVIDOR=?,DAT_COLETA=?,PCT_USER=?,PCT_NICE=?,PCT_SYSTEM=?,PCT_IOWAIT=?,PCT_STEAL=?,PCT_IDLE=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_CPU WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonCpu m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodServidor());
        //stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setDouble(idx++, m.getPctUser());
        stmt.setDouble(idx++, m.getPctNice());
        stmt.setDouble(idx++, m.getPctSystem());
        stmt.setDouble(idx++, m.getPctIowait());
        stmt.setDouble(idx++, m.getPctSteal());
        stmt.setDouble(idx++, m.getPctIdle());
    }

    @Override
    protected void setValues(MonCpu m, ResultSet rs) throws SQLException {
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        String dtColeta = rs.getString("DAT_COLETA");
        m.setDatColeta(TimeUtil.getDateTimeFromDatabase(dtColeta));
        m.setPctUser(rs.getDouble("PCT_USER"));
        m.setPctNice(rs.getDouble("PCT_NICE"));
        m.setPctSystem(rs.getDouble("PCT_SYSTEM"));
        m.setPctIowait(rs.getDouble("PCT_IOWAIT"));
        m.setPctSteal(rs.getDouble("PCT_STEAL"));
        m.setPctIdle(rs.getDouble("PCT_IDLE"));
    }
    
    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected MonCpu newInstance() {
        return new MonCpu();
    }
}
