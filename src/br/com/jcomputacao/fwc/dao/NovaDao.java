package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Nova;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NovaDao {

    public String getSelect() {
        return "SELECT N.ID,N.NAME FROM NOVA N";
    }

    public String getInsert() {
        return "INSERT INTO NOVA (ID,NAME) VALUES (?,?)";
    }

    public String getUpdate() {
        return "UPDATE NOVA SET ID=?,NAME=?";
    }

    public String getDelete() {
        return "DELETE FROM NOVA WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Nova n) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, n.getId());
        stmt.setString(idx++, n.getName());
    }

    protected void setValues(Nova n, ResultSet rs) throws SQLException {
        n.setId(rs.getLong("ID"));
        n.setName(rs.getString("NAME"));
    }
}
