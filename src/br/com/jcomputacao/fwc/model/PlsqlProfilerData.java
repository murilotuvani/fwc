package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class PlsqlProfilerData implements Serializable {

    private long runid;
    private long unitNumber;
    private long line;
    private long totalOccur;
    private long totalTime;
    private long minTime;
    private long maxTime;
    private long spare1;
    private long spare2;
    private long spare3;
    private long spare4;

    public long getRunid() {
        return this.runid;
    }

    public void setRunid(long runid) {
        this.runid = runid;
    }

    public long getUnitNumber() {
        return this.unitNumber;
    }

    public void setUnitNumber(long unitNumber) {
        this.unitNumber = unitNumber;
    }

    public long getLine() {
        return this.line;
    }

    public void setLine(long line) {
        this.line = line;
    }

    public long getTotalOccur() {
        return this.totalOccur;
    }

    public void setTotalOccur(long totalOccur) {
        this.totalOccur = totalOccur;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getMinTime() {
        return this.minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public long getMaxTime() {
        return this.maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public long getSpare1() {
        return this.spare1;
    }

    public void setSpare1(long spare1) {
        this.spare1 = spare1;
    }

    public long getSpare2() {
        return this.spare2;
    }

    public void setSpare2(long spare2) {
        this.spare2 = spare2;
    }

    public long getSpare3() {
        return this.spare3;
    }

    public void setSpare3(long spare3) {
        this.spare3 = spare3;
    }

    public long getSpare4() {
        return this.spare4;
    }

    public void setSpare4(long spare4) {
        this.spare4 = spare4;
    }
}
