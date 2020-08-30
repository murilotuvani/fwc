package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Log;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDao {

    public String getSelect() {
        return "SELECT L.ROTINA,L.DATA,L.MENSAGEM FROM LOG L";
    }

    public String getInsert() {
        return "INSERT INTO LOG (ROTINA,DATA,MENSAGEM) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE LOG SET ROTINA=?,DATA=?,MENSAGEM=?";
    }

    public String getDelete() {
        return "DELETE FROM LOG WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Log l) throws SQLException {
        int idx = 1;
        stmt.setString(idx++, l.getRotina());
        stmt.setDate(idx++, new java.sql.Date(l.getData().getTime()));
        stmt.setString(idx++, l.getMensagem());
    }

    protected void setValues(Log l, ResultSet rs) throws SQLException {
        l.setRotina(rs.getString("ROTINA"));
        l.setData(rs.getDate("DATA"));
        l.setMensagem(rs.getString("MENSAGEM"));
    }
}
