package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.model.Cliente;
import br.com.jcomputacao.fwc.model.MonServidor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonServidorDao extends Dao<MonServidor> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("codServidor", Integer.class, "COD_SERVIDOR", java.sql.Types.BIGINT);
    }


    @Override
    public String getSelect() {
        return "SELECT M.COD_SERVIDOR,M.COD_CLIENTE,M.NOM_SERVIDOR,M.FLG_ATIVO,M.TIP_SISTEMA_OPERACIONAL FROM MON_SERVIDOR M";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO MON_SERVIDOR (COD_SERVIDOR,COD_CLIENTE,NOM_SERVIDOR,FLG_ATIVO,TIP_SISTEMA_OPERACIONAL) VALUES (?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE MON_SERVIDOR SET COD_SERVIDOR=?,COD_CLIENTE=?,NOM_SERVIDOR=?,FLG_ATIVO=?,TIP_SISTEMA_OPERACIONAL=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM MON_SERVIDOR WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, MonServidor m) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, m.getCodServidor());
        stmt.setLong(idx++, m.getCodCliente());
        stmt.setString(idx++, m.getNomServidor());
        stmt.setString(idx++, m.getFlgAtivo());
        stmt.setString(idx++, m.getTipSistemaOperacional());
    }

    @Override
    protected void setValues(MonServidor m, ResultSet rs) throws SQLException {
        m.setCodServidor(rs.getLong("COD_SERVIDOR"));
        m.setCodCliente(rs.getLong("COD_CLIENTE"));
        m.setNomServidor(rs.getString("NOM_SERVIDOR"));
        m.setFlgAtivo(rs.getString("FLG_ATIVO"));
        m.setTipSistemaOperacional(rs.getString("TIP_SISTEMA_OPERACIONAL"));
    }
    
    public List<MonServidor> getListaPesquisa() throws DaoException {
        List<MonServidor> list = super.listar();
        ClienteDao cDao = new ClienteDao();
        List<Cliente> clientes = cDao.listar();
        Map<Long, Cliente> mapaClientes = new HashMap<Long, Cliente>();
        for (Cliente cliente : clientes) {
            mapaClientes.put(cliente.getCodCliente(), cliente);
        }
        for(MonServidor serv : list) {
            Cliente cliente = mapaClientes.get(serv.getCodCliente());
            if(cliente!=null) {
                serv.setNomCliente(cliente.getNomCliente());
            }
        }
        
        
        return list;
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected MonServidor newInstance() {
        return new MonServidor();
    }
}
