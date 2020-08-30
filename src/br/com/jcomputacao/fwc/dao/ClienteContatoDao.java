package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.ClienteContato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteContatoDao extends Dao<ClienteContato> {

    @Override
    public String getSelect() {
        return "SELECT C.COD_CONTATO,C.COD_CLIENTE,C.TIP_CONTATO,C.NUM_DDD,C.NUM_TELEFONE,C.NOM_CONTATO,C.NUM_RAMAL,C.VAL_EMAIL FROM CLIENTE_CONTATO C";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO CLIENTE_CONTATO (COD_CONTATO,COD_CLIENTE,TIP_CONTATO,NUM_DDD,NUM_TELEFONE,NOM_CONTATO,NUM_RAMAL,VAL_EMAIL) VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE CLIENTE_CONTATO SET COD_CONTATO=?,COD_CLIENTE=?,TIP_CONTATO=?,NUM_DDD=?,NUM_TELEFONE=?,NOM_CONTATO=?,NUM_RAMAL=?,VAL_EMAIL=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM CLIENTE_CONTATO WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, ClienteContato c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodContato());
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setString(idx++, c.getTipContato());
        stmt.setLong(idx++, c.getNumDdd());
        stmt.setLong(idx++, c.getNumTelefone());
        stmt.setString(idx++, c.getNomContato());
        stmt.setLong(idx++, c.getNumRamal());
        stmt.setString(idx++, c.getValEmail());
    }

    @Override
    protected void setValues(ClienteContato c, ResultSet rs) throws SQLException {
        c.setCodContato(rs.getLong("COD_CONTATO"));
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setTipContato(rs.getString("TIP_CONTATO"));
        c.setNumDdd(rs.getLong("NUM_DDD"));
        c.setNumTelefone(rs.getLong("NUM_TELEFONE"));
        c.setNomContato(rs.getString("NOM_CONTATO"));
        c.setNumRamal(rs.getLong("NUM_RAMAL"));
        c.setValEmail(rs.getString("VAL_EMAIL"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return null;
    }

    @Override
    protected ClienteContato newInstance() {
        return new ClienteContato();
    }

}