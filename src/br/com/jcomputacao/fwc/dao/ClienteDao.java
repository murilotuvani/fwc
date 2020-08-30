package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.fwc.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao extends Dao<Cliente> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codCliente", Integer.class, "COD_CLIENTE", java.sql.Types.BIGINT);
    }

    @Override
    public String getSelect() {
        return "SELECT C.COD_CLIENTE,C.NOM_CLIENTE,C.NOM_RAZAO_SOCIAL,C.FLG_MONITORAMENTO,C.FLG_ALERT\n"
                + ",C.NOM_RESPONSAVEL,C.DES_PROPOSTA_SERVICO,C.NUM_PROPOSTA\n"
                + "FROM CLIENTE C";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO CLIENTE (COD_CLIENTE,NOM_CLIENTE,NOM_RAZAO_SOCIAL,FLG_MONITORAMENTO,FLG_ALERT,NOM_RESPONSAVEL,DES_PROPOSTA_SERVICO,NUM_PROPOSTA) VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE CLIENTE SET COD_CLIENTE=?,NOM_CLIENTE=?,NOM_RAZAO_SOCIAL=?,FLG_MONITORAMENTO=?,FLG_ALERT=?,NOM_RESPONSAVEL=?,DES_PROPOSTA_SERVICO=?,NUM_PROPOSTA=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM CLIENTE WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, Cliente c) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, c.getCodCliente());
        stmt.setString(idx++, c.getNomCliente());
        stmt.setString(idx++, c.getNomRazaoSocial());
        stmt.setString(idx++, c.getFlgMonitoramento());
        stmt.setString(idx++, c.getFlgAlert());
        stmt.setString(idx++, c.getNomResponsavel());
        stmt.setString(idx++, c.getDesPropostaServico());
        stmt.setLong(idx++, c.getNumProposta());
    }

    @Override
    protected void setValues(Cliente c, ResultSet rs) throws SQLException {
        c.setCodCliente(rs.getLong("COD_CLIENTE"));
        c.setNomCliente(rs.getString("NOM_CLIENTE"));
        c.setNomRazaoSocial(rs.getString("NOM_RAZAO_SOCIAL"));
        c.setFlgMonitoramento(rs.getString("FLG_MONITORAMENTO"));
        c.setFlgAlert(rs.getString("FLG_ALERT"));
        c.setNomResponsavel(rs.getString("NOM_RESPONSAVEL"));
        c.setDesPropostaServico(rs.getString("DES_PROPOSTA_SERVICO"));
        c.setNumProposta(rs.getLong("NUM_PROPOSTA"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected Cliente newInstance() {
        return new Cliente();
    }
}
