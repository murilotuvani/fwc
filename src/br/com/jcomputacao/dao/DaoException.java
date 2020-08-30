/*
 *
 *
 */

package br.com.jcomputacao.dao;

/**
 * 16/05/2010 19:19:49
 * @author Murilo
 */
public class DaoException extends Exception {

    public DaoException(String string) {
        super(string);
    }

    public DaoException(String string, Throwable cause) {
        super(string, cause);
    }

}
