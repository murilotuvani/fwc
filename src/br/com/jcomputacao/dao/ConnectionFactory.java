/*
 *
 *
 */

package br.com.jcomputacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 24/09/2010 12:17:26
 * @author Murilo
 */
public class ConnectionFactory {
    private static final boolean test;
    private static final boolean singleConnection;
    private static Connection conn;
    
    static {
        String config = System.getProperty("fwc.report.test", "false");
        test = Boolean.parseBoolean(config);
        config = System.getProperty("fwc.report.singleConnection", "false");
        singleConnection = Boolean.parseBoolean(config);
    }

    public static Connection getConnection() throws SQLException {
        if(singleConnection) {
            if(conn == null) {
                conn = createConnection();
            }
            return conn;
        } else {
            return createConnection();
        }
    }

    private static Connection createConnection() throws SQLException {
        try {
            if (test) {
                String mysql = System.getProperty("sgbd.mysql", "false");
                if (Boolean.parseBoolean(mysql)) {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost/orami";
                    String user = "root";
                    String passwd = "murilo";
                    return DriverManager.getConnection(url, user, passwd);
                } else {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    String host     = System.getProperty("sql.host", "localhost");
                    String instance = System.getProperty("sql.instance", "XE");
                    String user     = System.getProperty("sql.user", "orauser");
                    String passwd   = System.getProperty("sql.passwd", "XXXXXX");
                    String url = "jdbc:oracle:thin:@//" + host + "/" + instance;
//                    String url = "jdbc:oracle:thin:@//10.0.0.2:1521/XE";
                    
                    return DriverManager.getConnection(url, user, passwd);
                }
            } else {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/fwcReports");
                return ds.getConnection();
            }
        } catch (Exception ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void devolver(java.sql.Connection conn) {
        try {
            conn.setAutoCommit(true);
            if (!singleConnection) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
        }
    }
}
