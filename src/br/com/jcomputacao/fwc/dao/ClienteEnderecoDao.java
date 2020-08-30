package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.ClienteEndereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteEnderecoDao {

    public String getSelect() {
        return "SELECT C.COD_ENDERECO,C.COD_TIPO,C.COD_CLIENTE,C.TIP_ENDERECO,C.NOM_LOGRADOURO,C.NOM_BAIRRO,C.COD_CIDADE,C.NUM_CEP FROM CLIENTE_ENDERECO C";
    }

    public String getInsert() {
        return "INSERT INTO CLIENTE_ENDERECO (COD_ENDERECO,COD_TIPO,COD_CLIENTE,TIP_ENDERECO,NOM_LOGRADOURO,NOM_BAIRRO,COD_CIDADE,NUM_CEP) VALUES (?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE CLIENTE_ENDERECO SET COD_ENDERECO=?,COD_TIPO=?,COD_CLIENTE=?,TIP_ENDERECO=?,NOM_LOGRADOURO=?,NOM_BAIRRO=?,COD_CIDADE=?,NUM_CEP=?";
    }

    public String getDelete() {
        return "DELETE FROM CLIENTE_ENDERECO WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, ClienteEndereco c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodEndereco());
        stmt.setLong(idx++, c.getCodTipo());
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setLong(idx++, c.getTipEndereco());
        stmt.setString(idx++, c.getNomLogradouro());
        stmt.setString(idx++, c.getNomBairro());
        stmt.setLong(idx++, c.getCodCidade());
        stmt.setLong(idx++, c.getNumCep());
    }

    protected void setValues(ClienteEndereco c, ResultSet rs) throws SQLException {
        c.setCodEndereco(rs.getLong("COD_ENDERECO"));
        c.setCodTipo(rs.getLong("COD_TIPO"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setTipEndereco(rs.getLong("TIP_ENDERECO"));
        c.setNomLogradouro(rs.getString("NOM_LOGRADOURO"));
        c.setNomBairro(rs.getString("NOM_BAIRRO"));
        c.setCodCidade(rs.getLong("COD_CIDADE"));
        c.setNumCep(rs.getLong("NUM_CEP"));
    }
}
