package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.Atividade;
import br.com.jcomputacao.util.TimeUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtividadeDao extends Dao<Atividade> {

    @Override
    public String getSelect() {
        return "SELECT A.COD_ATIVIDADE,A.COD_TIPO_ATIVIDADE,A.NUM_CHAMADO,A.COD_CLIENTE,A.COD_SERVIDOR,\n"
                + "TO_CHAR(A.DAT_INICIO,'YYYY-MM-DD HH24:MI:SS') DAT_INICIO,\n"
                + "TO_CHAR(A.DAT_FIM,'YYYY-MM-DD HH24:MI:SS') DAT_FIM,\n"
                + "A.HOR_INTERVALO,A.DES_MOTIVO,A.DES_ACAO_TOMADA,A.NOM_USUARIO,A.FLG_CONTRATO,A.FLG_INTERNO\n"
                + "FROM ATIVIDADE A";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO ATIVIDADE (COD_ATIVIDADE,COD_TIPO_ATIVIDADE,NUM_CHAMADO,COD_CLIENTE,COD_SERVIDOR,DAT_INICIO,DAT_FIM,HOR_INTERVALO,DES_MOTIVO,DES_ACAO_TOMADA,NOM_USUARIO,FLG_CONTRATO,FLG_INTERNO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE ATIVIDADE SET COD_ATIVIDADE=?,COD_TIPO_ATIVIDADE=?,NUM_CHAMADO=?,COD_CLIENTE=?,COD_SERVIDOR=?,DAT_INICIO=?,DAT_FIM=?,HOR_INTERVALO=?,DES_MOTIVO=?,DES_ACAO_TOMADA=?,NOM_USUARIO=?,FLG_CONTRATO=?,FLG_INTERNO=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM ATIVIDADE WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, Atividade a) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, a.getCodAtividade());
        stmt.setLong(idx++, a.getCodTipoAtividade());
        stmt.setLong(idx++, a.getNumChamado());
        stmt.setLong(idx++, a.getCodCliente());
        stmt.setLong(idx++, a.getCodServidor());
        stmt.setDate(idx++, new java.sql.Date(a.getDatInicio().getTime()));
        stmt.setDate(idx++, new java.sql.Date(a.getDatFim().getTime()));
        stmt.setLong(idx++, a.getHorIntervalo());
        stmt.setString(idx++, a.getDesMotivo());
        stmt.setString(idx++, a.getDesAcaoTomada());
        stmt.setString(idx++, a.getNomUsuario());
        stmt.setString(idx++, a.getFlgContrato());
        stmt.setString(idx++, a.getFlgInterno());
    }

    @Override
    protected void setValues(Atividade a, ResultSet rs) throws SQLException {
        a.setCodAtividade(rs.getLong("COD_ATIVIDADE"));
        a.setCodTipoAtividade(rs.getLong("COD_TIPO_ATIVIDADE"));
        a.setNumChamado(rs.getLong("NUM_CHAMADO"));
        a.setCodCliente(rs.getLong("COD_CLIENTE"));
        a.setCodServidor(rs.getLong("COD_SERVIDOR"));
        String data = rs.getString("DAT_INICIO");
        a.setDatInicio(TimeUtil.getDateTimeFromDatabase(data));
        data = rs.getString("DAT_FIM");
        a.setDatFim(TimeUtil.getDateTimeFromDatabase(data));
        a.setHorIntervalo(rs.getLong("HOR_INTERVALO"));
        a.setDesMotivo(rs.getString("DES_MOTIVO"));
        a.setDesAcaoTomada(rs.getString("DES_ACAO_TOMADA"));
        a.setNomUsuario(rs.getString("NOM_USUARIO"));
        a.setFlgContrato(rs.getString("FLG_CONTRATO"));
        a.setFlgInterno(rs.getString("FLG_INTERNO"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected Atividade newInstance() {
        return new Atividade();
    }
}
