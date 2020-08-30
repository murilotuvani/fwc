package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.PlsqlProfilerUnits;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlsqlProfilerUnitsDao {

    public String getSelect() {
        return "SELECT P.RUNID,P.UNIT_NUMBER,P.UNIT_TYPE,P.UNIT_OWNER,P.UNIT_NAME,P.UNIT_TIMESTAMP,P.TOTAL_TIME,P.SPARE1,P.SPARE2 FROM PLSQL_PROFILER_UNITS P";
    }

    public String getInsert() {
        return "INSERT INTO PLSQL_PROFILER_UNITS (RUNID,UNIT_NUMBER,UNIT_TYPE,UNIT_OWNER,UNIT_NAME,UNIT_TIMESTAMP,TOTAL_TIME,SPARE1,SPARE2) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE PLSQL_PROFILER_UNITS SET RUNID=?,UNIT_NUMBER=?,UNIT_TYPE=?,UNIT_OWNER=?,UNIT_NAME=?,UNIT_TIMESTAMP=?,TOTAL_TIME=?,SPARE1=?,SPARE2=?";
    }

    public String getDelete() {
        return "DELETE FROM PLSQL_PROFILER_UNITS WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, PlsqlProfilerUnits p) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, p.getRunid());
        stmt.setLong(idx++, p.getUnitNumber());
        stmt.setString(idx++, p.getUnitType());
        stmt.setString(idx++, p.getUnitOwner());
        stmt.setString(idx++, p.getUnitName());
        stmt.setDate(idx++, new java.sql.Date(p.getUnitTimestamp().getTime()));
        stmt.setLong(idx++, p.getTotalTime());
        stmt.setLong(idx++, p.getSpare1());
        stmt.setLong(idx++, p.getSpare2());
    }

    protected void setValues(PlsqlProfilerUnits p, ResultSet rs) throws SQLException {
        p.setRunid(rs.getLong("RUNID"));
        p.setUnitNumber(rs.getLong("UNIT_NUMBER"));
        p.setUnitType(rs.getString("UNIT_TYPE"));
        p.setUnitOwner(rs.getString("UNIT_OWNER"));
        p.setUnitName(rs.getString("UNIT_NAME"));
        p.setUnitTimestamp(rs.getDate("UNIT_TIMESTAMP"));
        p.setTotalTime(rs.getLong("TOTAL_TIME"));
        p.setSpare1(rs.getLong("SPARE1"));
        p.setSpare2(rs.getLong("SPARE2"));
    }
}
