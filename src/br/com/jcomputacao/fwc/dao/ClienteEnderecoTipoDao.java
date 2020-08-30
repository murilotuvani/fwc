package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.ClienteEnderecoTipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteEnderecoTipoDao {

    public String getSelect() {
        return "SELECT C.COD_TIPO,C.NOM_TIPO,C.NOM_TIPO_ABREVIADO FROM CLIENTE_ENDERECO_TIPO C";
    }

    public String getInsert() {
        return "INSERT INTO CLIENTE_ENDERECO_TIPO (COD_TIPO,NOM_TIPO,NOM_TIPO_ABREVIADO) VALUES (?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CLIENTE_ENDERECO_TIPO SET COD_TIPO=?,NOM_TIPO=?,NOM_TIPO_ABREVIADO=?";
    }

    public String getDelete() {
        return "DELETE FROM CLIENTE_ENDERECO_TIPO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, ClienteEnderecoTipo c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodTipo());
        stmt.setString(idx++, c.getNomTipo());
        stmt.setString(idx++, c.getNomTipoAbreviado());
    }

    protected void setValues(ClienteEnderecoTipo c, ResultSet rs) throws SQLException {
        c.setCodTipo(rs.getLong("COD_TIPO"));
        c.setNomTipo(rs.getString("NOM_TIPO"));
        c.setNomTipoAbreviado(rs.getString("NOM_TIPO_ABREVIADO"));
    }
}
