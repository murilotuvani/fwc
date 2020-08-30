package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Mlog$TesteDois;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mlog$TesteDoisDao {

    public String getSelect() {
        return "SELECT M.M_ROW$$,M.SNAPTIME$$,M.DMLTYPE$$,M.OLD_NEW$$,M.CHANGE_VECTOR$$ FROM MLOG$_TESTE_DOIS M";
    }

    public String getInsert() {
        return "INSERT INTO MLOG$_TESTE_DOIS (M_ROW$$,SNAPTIME$$,DMLTYPE$$,OLD_NEW$$,CHANGE_VECTOR$$) VALUES (?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MLOG$_TESTE_DOIS SET M_ROW$$=?,SNAPTIME$$=?,DMLTYPE$$=?,OLD_NEW$$=?,CHANGE_VECTOR$$=?";
    }

    public String getDelete() {
        return "DELETE FROM MLOG$_TESTE_DOIS WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Mlog$TesteDois m) throws SQLException {
        int idx = 1;
        stmt.setString(idx++, m.getMRow$$());
        stmt.setDate(idx++, new java.sql.Date(m.getSnaptime$$().getTime()));
        stmt.setString(idx++, m.getDmltype$$());
        stmt.setString(idx++, m.getOldNew$$());
//        stmt.setBlob(idx++, m.getChangeVector$$());
    }

    protected void setValues(Mlog$TesteDois m, ResultSet rs) throws SQLException {
        m.setMRow$$(rs.getString("M_ROW$$"));
        m.setSnaptime$$(rs.getDate("SNAPTIME$$"));
        m.setDmltype$$(rs.getString("DMLTYPE$$"));
        m.setOldNew$$(rs.getString("OLD_NEW$$"));
//        m.setChangeVector$$(rs.getBlob("CHANGE_VECTOR$$"));
    }
}
