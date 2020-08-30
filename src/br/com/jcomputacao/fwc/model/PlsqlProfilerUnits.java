package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class PlsqlProfilerUnits implements Serializable {

    private long runid;
    private long unitNumber;
    private String unitType;
    private String unitOwner;
    private String unitName;
    private Date unitTimestamp;
    private long totalTime;
    private long spare1;
    private long spare2;

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

    public String getUnitType() {
        return this.unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitOwner() {
        return this.unitOwner;
    }

    public void setUnitOwner(String unitOwner) {
        this.unitOwner = unitOwner;
    }

    public String getUnitName() {
        return this.unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Date getUnitTimestamp() {
        return this.unitTimestamp;
    }

    public void setUnitTimestamp(Date unitTimestamp) {
        this.unitTimestamp = unitTimestamp;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
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
}
