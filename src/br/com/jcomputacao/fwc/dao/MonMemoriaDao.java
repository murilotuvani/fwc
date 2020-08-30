package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.MonMemoria;
import br.com.jcomputacao.util.TimeUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonMemoriaDao extends Dao<MonMemoria> {

    @Override
    public String getSelect() {
        return "SELECT M.COD_SERVIDOR,TO_CHAR(M.DAT_COLETA,'YYYY-MM-DD HH24:MI:SS') DAT_COLETA,\n"
                + "M.VAL_UTILIZADO,M.VAL_DISPONIVEL FROM MON_MEMORIA M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_MEMORIA (COD_SERVIDOR,DAT_COLETA,VAL_UTILIZADO,VAL_DISPONIVEL) VALUES (?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_MEMORIA SET COD_SERVIDOR=?,DAT_COLETA=?,VAL_UTILIZADO=?,VAL_DISPONIVEL=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_MEMORIA WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonMemoria m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setDouble(idx++, m.getValUtilizado());
        stmt.setDouble(idx++, m.getValDisponivel());
    }

    @Override
    protected void setValues(MonMemoria m, ResultSet rs) throws SQLException {
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        String dtColeta = rs.getString("DAT_COLETA");
        m.setDatColeta(TimeUtil.getDateTimeFromDatabase(dtColeta));
        m.setValUtilizado(rs.getDouble("VAL_UTILIZADO"));
        m.setValDisponivel(rs.getDouble("VAL_DISPONIVEL"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected MonMemoria newInstance() {
        return new MonMemoria();
    }
}
