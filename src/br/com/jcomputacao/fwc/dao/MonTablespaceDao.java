package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.MonTablespace;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonTablespaceDao extends Dao<MonTablespace> {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String getSelect() {
        return "SELECT M.COD_BANCO_DADOS,TO_CHAR(M.DAT_COLETA,'YYYY-MM-DD HH24:MI:SS') DAT_COLETA,\n"
                + "M.NOM_TABLESPACE,M.VAL_ALOCADO,M.VAL_LIVRE,M.VAL_MAXIMO FROM MON_TABLESPACE M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_TABLESPACE (COD_BANCO_DADOS,DAT_COLETA,NOM_TABLESPACE,VAL_ALOCADO,VAL_LIVRE,VAL_MAXIMO) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_TABLESPACE SET COD_BANCO_DADOS=?,DAT_COLETA=?,NOM_TABLESPACE=?,VAL_ALOCADO=?,VAL_LIVRE=?,VAL_MAXIMO=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_TABLESPACE WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonTablespace m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodBancoDados());
        stmt.setDate(idx++, new java.sql.Date(m.getDatColeta().getTime()));
        stmt.setString(idx++, m.getNomTablespace());
        stmt.setDouble(idx++, m.getValAlocado());
        stmt.setDouble(idx++, m.getValLivre());
        stmt.setDouble(idx++, m.getValMaximo());
    }

    @Override
    protected void setValues(MonTablespace m, ResultSet rs) throws SQLException {
        m.setCodBancoDados(rs.getLong("COD_BANCO_DADOS"));
        //m.setDatColeta(rs.getDate("DAT_COLETA"));
        String dtColeta = rs.getString("DAT_COLETA");
        if (dtColeta != null || "".equals(dtColeta)) {
            try {
                m.setDatColeta(sdf.parse(dtColeta));
            } catch (ParseException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Erro ao tentar converter a data", ex);
            }
        }
        m.setNomTablespace(rs.getString("NOM_TABLESPACE"));
        m.setValAlocado(rs.getDouble("VAL_ALOCADO"));
        m.setValLivre(rs.getDouble("VAL_LIVRE"));
        m.setValMaximo(rs.getDouble("VAL_MAXIMO"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected MonTablespace newInstance() {
        return new MonTablespace();
    }
}
