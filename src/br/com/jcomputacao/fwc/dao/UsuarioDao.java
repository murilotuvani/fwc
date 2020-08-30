package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    public String getSelect() {
        return "SELECT U.NOM_USUARIO,U.VAL_SENHA,U.DAT_ALTERACAO,U.NOM_FUNCIONARIO FROM USUARIO U";
    }

    public String getInsert() {
        return "INSERT INTO USUARIO (NOM_USUARIO,VAL_SENHA,DAT_ALTERACAO,NOM_FUNCIONARIO) VALUES (?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE USUARIO SET NOM_USUARIO=?,VAL_SENHA=?,DAT_ALTERACAO=?,NOM_FUNCIONARIO=?";
    }

    public String getDelete() {
        return "DELETE FROM USUARIO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Usuario u) throws SQLException {
        int idx = 1;
        stmt.setString(idx++, u.getNomUsuario());
        stmt.setString(idx++, u.getValSenha());
        stmt.setDate(idx++, new java.sql.Date(u.getDatAlteracao().getTime()));
        stmt.setString(idx++, u.getNomFuncionario());
    }

    protected void setValues(Usuario u, ResultSet rs) throws SQLException {
        u.setNomUsuario(rs.getString("NOM_USUARIO"));
        u.setValSenha(rs.getString("VAL_SENHA"));
        u.setDatAlteracao(rs.getDate("DAT_ALTERACAO"));
        u.setNomFuncionario(rs.getString("NOM_FUNCIONARIO"));
    }
}
