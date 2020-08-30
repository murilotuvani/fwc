package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.Rupd$Nova;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rupd$NovaDao {

    public String getSelect() {
        return "SELECT R.ID,R.DMLTYPE$$,R.SNAPID,R.CHANGE_VECTOR$$ FROM RUPD$_NOVA R";
    }

    public String getInsert() {
        return "INSERT INTO RUPD$_NOVA (ID,DMLTYPE$$,SNAPID,CHANGE_VECTOR$$) VALUES (?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE RUPD$_NOVA SET ID=?,DMLTYPE$$=?,SNAPID=?,CHANGE_VECTOR$$=?";
    }

    public String getDelete() {
        return "DELETE FROM RUPD$_NOVA WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, Rupd$Nova r) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, r.getId());
        stmt.setString(idx++, r.getDmltype$$());
        stmt.setLong(idx++, r.getSnapid());
//        stmt.setBlob(idx++, r.getChangeVector$$());
    }

    protected void setValues(Rupd$Nova r, ResultSet rs) throws SQLException {
        r.setId(rs.getLong("ID"));
        r.setDmltype$$(rs.getString("DMLTYPE$$"));
        r.setSnapid(rs.getLong("SNAPID"));
        Blob blob = rs.getBlob("ARQUIVO_CONTEUDO");
        long length = blob.length();
        r.setChangeVector$$(blob.getBytes(1, (int) length));
    }
}
