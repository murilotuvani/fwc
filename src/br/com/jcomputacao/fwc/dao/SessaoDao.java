package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Sessao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessaoDao {

    public String getSelect() {
        return "SELECT S.ID_SESSAO,S.NOM_USUARIO,S.DAT_CRIACAO FROM SESSAO S";
    }

    public String getInsert() {
        return "INSERT INTO SESSAO (ID_SESSAO,NOM_USUARIO,DAT_CRIACAO) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE SESSAO SET ID_SESSAO=?,NOM_USUARIO=?,DAT_CRIACAO=?";
    }

    public String getDelete() {
        return "DELETE FROM SESSAO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Sessao s) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, s.getIdSessao());
        stmt.setString(idx++, s.getNomUsuario());
        stmt.setDate(idx++, new java.sql.Date(s.getDatCriacao().getTime()));
    }

    protected void setValues(Sessao s, ResultSet rs) throws SQLException {
        s.setIdSessao(rs.getLong("ID_SESSAO"));
        s.setNomUsuario(rs.getString("NOM_USUARIO"));
        s.setDatCriacao(rs.getDate("DAT_CRIACAO"));
    }
}
