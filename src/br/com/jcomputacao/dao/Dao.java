package br.com.jcomputacao.dao;

import br.com.jcomputacao.util.StringUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 23/10/2010 10:15:06
 * @author Murilo
 */
public abstract class Dao<T> {

    protected abstract ChavePrimariaDescritor getChavePrimariaDescritor();

    protected abstract T newInstance();

    protected abstract String getSelect();

    protected abstract String getInsert();

    protected abstract String getUpdate();

    protected abstract String getDelete();

    protected abstract void prepareUpdate(PreparedStatement stmt, T t) throws SQLException, DaoException;

    protected abstract void setValues(T t, ResultSet rs) throws SQLException, DaoException;

    protected void gerarChavePrimaria(T t) throws SQLException {
    }

    ;

    public T buscar(Object... chavePrimaria) throws DaoException {
        ChavePrimariaDescritor descritor = getChavePrimariaDescritor();
        if (chavePrimaria == null || chavePrimaria.length != descritor.getCampos().size()) {
            throw new IllegalArgumentException("Chaves nulas ou numero de argumentos inválidos");
        }

        String comando = getSelect();
        comando += comandoComChavePrimaria();

        Connection conn = null;
        T t = null;
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(comando);
            preecheChavePrimaria(stmt,chavePrimaria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                t = newInstance();
                setValues(t, rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar recuperar o cadastro", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return t;
    }

    public T salvar(T t) throws DaoException {
        String comando = getInsert();
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            boolean genKeys = getChavePrimariaDescritor().isAutogerada();
            if (genKeys) {
                stmt = conn.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
            } else {
                gerarChavePrimaria(t);
                stmt = conn.prepareStatement(comando);
            }
            prepareUpdate(stmt, t);
            int afetados = stmt.executeUpdate();
            if (getChavePrimariaDescritor().isAutogerada()) {
                ResultSet chavesGeradas = stmt.getGeneratedKeys();
                List<ChavePrimariaDescritorCampo> campos = getChavePrimariaDescritor().getCampos();
                for (ChavePrimariaDescritorCampo campo : campos) {
                    String coluna = campo.getColunaSql();
                    Long obj = chavesGeradas.getLong(coluna);
                    String atributo = campo.getAtributo();
                    atributo = "set" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
                    Method method = t.getClass().getMethod(atributo, campo.getTipo());
                    method.invoke(t, obj);
                }
                chavesGeradas.close();
            }

            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINER, "Inseriu {0} registro(s)", afetados);
        } catch (Exception ex) {
            throw new DaoException("Erro ao tentar inserir o cadastro", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return t;
    }

    public T alterar(T t) throws DaoException {
        Object[] chavePrimaria = extrairValoresChavePrimaria(t);

        String comando = getUpdate();
//        comando += comandoComChavePrimaria();
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(comando);
            prepareUpdate(stmt, t);
            preecheChavePrimaria(stmt, chavePrimaria);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar recuperar o cadastro", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return t;
    }

    public List<T> listar() throws DaoException {
        return listar(null);
    }

    public List<T> listar(String where) throws DaoException {
        List<T> lista = new ArrayList<T>();
        Connection conn = null;
        String comando = getSelect();

        if (where != null && !"".equals(where)) {
            if (!where.contains("WHERE")) {
                comando += " WHERE ";
            }
            comando += where;
        }

        try {
            conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            System.out.println(comando);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINER, comando);
            ResultSet rs = stmt.executeQuery(comando);

            while (rs.next()) {
                T entidade = newInstance();
                setValues(entidade, rs);
                lista.add(entidade);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tetnar ler o(s) registro(s)", ex);
        } finally {
            if (conn != null) {
                ConnectionFactory.devolver(conn);
            }
        }
        return lista;
    }

    private void preecheChavePrimaria(PreparedStatement stmt, Object[] chavePrimaria) throws SQLException {
        ChavePrimariaDescritor descritor = getChavePrimariaDescritor();
        int parametros = stmt.getParameterMetaData().getParameterCount();
        parametros -= (descritor.getCampos().size() - 1);
        int idx = 0;

        for (ChavePrimariaDescritorCampo campo : descritor.getCampos()) {
            switch (campo.getTipoSql()) {
                case java.sql.Types.INTEGER:
                    stmt.setInt((idx + parametros), new Integer(chavePrimaria[idx].toString()));
                    break;
                case java.sql.Types.BIGINT:
                    stmt.setLong((idx + parametros), new Long(chavePrimaria[idx].toString()));
                    break;
            }
            idx++;
        }
    }

    private String comandoComChavePrimaria() {
        String where = " WHERE ";
        boolean first = true;
        for (ChavePrimariaDescritorCampo campo : getChavePrimariaDescritor().getCampos()) {
            if (first) {
                first = false;
            } else {
                where += " AND ";
            }
            where += campo.getColunaSql() + "=?";
        }
        return where;
    }

    private Object[] extrairValoresChavePrimaria(T t) throws DaoException {
        ChavePrimariaDescritor descritor = getChavePrimariaDescritor();
        Object[] valores = new Object[descritor.getCampos().size()];

        int i = 0;

        for (ChavePrimariaDescritorCampo campo : descritor.getCampos()) {
            String nomeMetodo = "get"
                    + campo.getAtributo().substring(0, 1).toUpperCase()
                    + campo.getAtributo().substring(1);

            try {
                Method method = t.getClass().getMethod(nomeMetodo);
                valores[i] = method.invoke(t);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                throw new DaoException("Não encontrou o metodo "
                        + nomeMetodo + " em " + t.getClass().getName());
            } catch (SecurityException ex) {
                throw new DaoException("Erro de segurando ao tentar executar o metodo "
                        + nomeMetodo + " em " + t.getClass().getName());
            }
            i++;
        }

        return valores;
    }

    protected void setNullSafe(PreparedStatement stmt, Integer value, int index) throws SQLException {
        if (value == null || value.intValue() == 0) {
            stmt.setNull(index, java.sql.Types.INTEGER);
        } else {
            stmt.setInt(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Long value, int index) throws SQLException {
        if (value == null || value.longValue() == 0) {
            stmt.setNull(index, java.sql.Types.BIGINT);
        } else {
            stmt.setLong(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, String value, int index) throws SQLException {
        if (StringUtil.isNull(value)) {
            stmt.setNull(index, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Double value, int index) throws SQLException {
        if (value == null || value == 0) {
            stmt.setNull(index, java.sql.Types.DOUBLE);
        } else {
            stmt.setDouble(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Float value, int index) throws SQLException {
        if (value == null || value == 0) {
            stmt.setNull(index, java.sql.Types.DOUBLE);
        } else {
            stmt.setDouble(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, java.util.Date date, int idx) throws SQLException {
        if (date == null) {
            stmt.setNull(idx++, java.sql.Types.TIMESTAMP);
        } else {
            stmt.setTimestamp(idx++, new java.sql.Timestamp(date.getTime()));
        }
    }

    protected void setNullSafe(PreparedStatement stmt, boolean value, int index) throws SQLException {
        if ((value == true) || (value == false)) {
            stmt.setBoolean(index, value);
        } else {
            stmt.setNull(index, java.sql.Types.BOOLEAN);
        }
    }
}
