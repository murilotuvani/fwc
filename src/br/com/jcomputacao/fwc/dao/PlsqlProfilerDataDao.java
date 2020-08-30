package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.PlsqlProfilerData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlsqlProfilerDataDao {

    public String getSelect() {
        return "SELECT P.RUNID,P.UNIT_NUMBER,P.LINE#,P.TOTAL_OCCUR,P.TOTAL_TIME,P.MIN_TIME,P.MAX_TIME,P.SPARE1,P.SPARE2,P.SPARE3,P.SPARE4 FROM PLSQL_PROFILER_DATA P";
    }

    public String getInsert() {
        return "INSERT INTO PLSQL_PROFILER_DATA (RUNID,UNIT_NUMBER,LINE#,TOTAL_OCCUR,TOTAL_TIME,MIN_TIME,MAX_TIME,SPARE1,SPARE2,SPARE3,SPARE4) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE PLSQL_PROFILER_DATA SET RUNID=?,UNIT_NUMBER=?,LINE#=?,TOTAL_OCCUR=?,TOTAL_TIME=?,MIN_TIME=?,MAX_TIME=?,SPARE1=?,SPARE2=?,SPARE3=?,SPARE4=?";
    }

    public String getDelete() {
        return "DELETE FROM PLSQL_PROFILER_DATA WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, PlsqlProfilerData p) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, p.getRunid());
        stmt.setLong(idx++, p.getUnitNumber());
        stmt.setLong(idx++, p.getLine());
        stmt.setLong(idx++, p.getTotalOccur());
        stmt.setLong(idx++, p.getTotalTime());
        stmt.setLong(idx++, p.getMinTime());
        stmt.setLong(idx++, p.getMaxTime());
        stmt.setLong(idx++, p.getSpare1());
        stmt.setLong(idx++, p.getSpare2());
        stmt.setLong(idx++, p.getSpare3());
        stmt.setLong(idx++, p.getSpare4());
    }

    protected void setValues(PlsqlProfilerData p, ResultSet rs) throws SQLException {
        p.setRunid(rs.getLong("RUNID"));
        p.setUnitNumber(rs.getLong("UNIT_NUMBER"));
        p.setLine(rs.getLong("LINE#"));
        p.setTotalOccur(rs.getLong("TOTAL_OCCUR"));
        p.setTotalTime(rs.getLong("TOTAL_TIME"));
        p.setMinTime(rs.getLong("MIN_TIME"));
        p.setMaxTime(rs.getLong("MAX_TIME"));
        p.setSpare1(rs.getLong("SPARE1"));
        p.setSpare2(rs.getLong("SPARE2"));
        p.setSpare3(rs.getLong("SPARE3"));
        p.setSpare4(rs.getLong("SPARE4"));
    }
}
