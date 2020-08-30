package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Mlog$Nova;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mlog$NovaDao {

    public String getSelect() {
        return "SELECT M.ID,M.SNAPTIME$$,M.DMLTYPE$$,M.OLD_NEW$$,M.CHANGE_VECTOR$$ FROM MLOG$_NOVA M";
    }

    public String getInsert() {
        return "INSERT INTO MLOG$_NOVA (ID,SNAPTIME$$,DMLTYPE$$,OLD_NEW$$,CHANGE_VECTOR$$) VALUES (?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE MLOG$_NOVA SET ID=?,SNAPTIME$$=?,DMLTYPE$$=?,OLD_NEW$$=?,CHANGE_VECTOR$$=?";
    }

    public String getDelete() {
        return "DELETE FROM MLOG$_NOVA WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Mlog$Nova m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getId());
        stmt.setDate(idx++, new java.sql.Date(m.getSnaptime$$().getTime()));
        stmt.setString(idx++, m.getDmltype$$());
        stmt.setString(idx++, m.getOldNew$$());
//        stmt.setBlob(idx++, m.getChangeVector$$());
    }

    protected void setValues(Mlog$Nova m, ResultSet rs) throws SQLException {
        m.setId(rs.getLong("ID"));
        m.setSnaptime$$(rs.getDate("SNAPTIME$$"));
        m.setDmltype$$(rs.getString("DMLTYPE$$"));
        m.setOldNew$$(rs.getString("OLD_NEW$$"));
//        m.setChangeVector$$(rs.getBlob("CHANGE_VECTOR$$"));
    }
}
