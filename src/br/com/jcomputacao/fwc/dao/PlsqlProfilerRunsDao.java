package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.PlsqlProfilerRuns;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlsqlProfilerRunsDao {

    public String getSelect() {
        return "SELECT P.RUNID,P.RELATED_RUN,P.RUN_OWNER,P.RUN_DATE,P.RUN_COMMENT,P.RUN_TOTAL_TIME,P.RUN_SYSTEM_INFO,P.RUN_COMMENT1,P.SPARE1 FROM PLSQL_PROFILER_RUNS P";
    }

    public String getInsert() {
        return "INSERT INTO PLSQL_PROFILER_RUNS (RUNID,RELATED_RUN,RUN_OWNER,RUN_DATE,RUN_COMMENT,RUN_TOTAL_TIME,RUN_SYSTEM_INFO,RUN_COMMENT1,SPARE1) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public String getUpdate() {
        return "UPDATE PLSQL_PROFILER_RUNS SET RUNID=?,RELATED_RUN=?,RUN_OWNER=?,RUN_DATE=?,RUN_COMMENT=?,RUN_TOTAL_TIME=?,RUN_SYSTEM_INFO=?,RUN_COMMENT1=?,SPARE1=?";
    }

    public String getDelete() {
        return "DELETE FROM PLSQL_PROFILER_RUNS WHERE ID=?";
    }

    protected void prepareUpdate(PreparedStatement stmt, PlsqlProfilerRuns p) throws SQLException {
        int idx = 1;
        stmt.setLong(idx++, p.getRunid());
        stmt.setLong(idx++, p.getRelatedRun());
        stmt.setString(idx++, p.getRunOwner());
        stmt.setDate(idx++, new java.sql.Date(p.getRunDate().getTime()));
        stmt.setString(idx++, p.getRunComment());
        stmt.setLong(idx++, p.getRunTotalTime());
        stmt.setString(idx++, p.getRunSystemInfo());
        stmt.setString(idx++, p.getRunComment1());
        stmt.setString(idx++, p.getSpare1());
    }

    protected void setValues(PlsqlProfilerRuns p, ResultSet rs) throws SQLException {
        p.setRunid(rs.getLong("RUNID"));
        p.setRelatedRun(rs.getLong("RELATED_RUN"));
        p.setRunOwner(rs.getString("RUN_OWNER"));
        p.setRunDate(rs.getDate("RUN_DATE"));
        p.setRunComment(rs.getString("RUN_COMMENT"));
        p.setRunTotalTime(rs.getLong("RUN_TOTAL_TIME"));
        p.setRunSystemInfo(rs.getString("RUN_SYSTEM_INFO"));
        p.setRunComment1(rs.getString("RUN_COMMENT1"));
        p.setSpare1(rs.getString("SPARE1"));
    }
}
