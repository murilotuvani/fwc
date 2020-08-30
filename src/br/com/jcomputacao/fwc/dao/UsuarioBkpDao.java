package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.UsuarioBkp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBkpDao {

    public String getSelect() {
        return "SELECT U.NOM_USUARIO,U.VAL_SENHA,U.DAT_ALTERACAO FROM USUARIO_BKP U";
    }

    public String getInsert() {
        return "INSERT INTO USUARIO_BKP (NOM_USUARIO,VAL_SENHA,DAT_ALTERACAO) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE USUARIO_BKP SET NOM_USUARIO=?,VAL_SENHA=?,DAT_ALTERACAO=?";
    }

    public String getDelete() {
        return "DELETE FROM USUARIO_BKP WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, UsuarioBkp u) throws SQLException {
        int idx = 1;
        stmt.setString(idx++, u.getNomUsuario());
        stmt.setString(idx++, u.getValSenha());
        stmt.setDate(idx++, new java.sql.Date(u.getDatAlteracao().getTime()));
    }

    protected void setValues(UsuarioBkp u, ResultSet rs) throws SQLException {
        u.setNomUsuario(rs.getString("NOM_USUARIO"));
        u.setValSenha(rs.getString("VAL_SENHA"));
        u.setDatAlteracao(rs.getDate("DAT_ALTERACAO"));
    }
}
