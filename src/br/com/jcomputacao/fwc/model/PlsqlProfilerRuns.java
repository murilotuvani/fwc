package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class PlsqlProfilerRuns implements Serializable {

    private long runid;
    private long relatedRun;
    private String runOwner;
    private Date runDate;
    private String runComment;
    private long runTotalTime;
    private String runSystemInfo;
    private String runComment1;
    private String spare1;

    public long getRunid() {
        return this.runid;
    }

    public void setRunid(long runid) {
        this.runid = runid;
    }

    public long getRelatedRun() {
        return this.relatedRun;
    }

    public void setRelatedRun(long relatedRun) {
        this.relatedRun = relatedRun;
    }

    public String getRunOwner() {
        return this.runOwner;
    }

    public void setRunOwner(String runOwner) {
        this.runOwner = runOwner;
    }

    public Date getRunDate() {
        return this.runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public String getRunComment() {
        return this.runComment;
    }

    public void setRunComment(String runComment) {
        this.runComment = runComment;
    }

    public long getRunTotalTime() {
        return this.runTotalTime;
    }

    public void setRunTotalTime(long runTotalTime) {
        this.runTotalTime = runTotalTime;
    }

    public String getRunSystemInfo() {
        return this.runSystemInfo;
    }

    public void setRunSystemInfo(String runSystemInfo) {
        this.runSystemInfo = runSystemInfo;
    }

    public String getRunComment1() {
        return this.runComment1;
    }

    public void setRunComment1(String runComment1) {
        this.runComment1 = runComment1;
    }

    public String getSpare1() {
        return this.spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }
}
