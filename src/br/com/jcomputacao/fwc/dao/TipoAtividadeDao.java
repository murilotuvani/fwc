package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.TipoAtividade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoAtividadeDao extends Dao<TipoAtividade> {

    @Override
    public String getSelect() {
        return "SELECT T.COD_TIPO_ATIVIDADE,T.NOM_TIPO_ATIVIDADE FROM TIPO_ATIVIDADE T";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO TIPO_ATIVIDADE (COD_TIPO_ATIVIDADE,NOM_TIPO_ATIVIDADE) VALUES (?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE TIPO_ATIVIDADE SET COD_TIPO_ATIVIDADE=?,NOM_TIPO_ATIVIDADE=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM TIPO_ATIVIDADE WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, TipoAtividade t) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, t.getCodTipoAtividade());
        stmt.setString(idx++, t.getNomTipoAtividade());
    }

    @Override
    protected void setValues(TipoAtividade t, ResultSet rs) throws SQLException {
        t.setCodTipoAtividade(rs.getLong("COD_TIPO_ATIVIDADE"));
        t.setNomTipoAtividade(rs.getString("NOM_TIPO_ATIVIDADE"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected TipoAtividade newInstance() {
        return new TipoAtividade();
    }
}
