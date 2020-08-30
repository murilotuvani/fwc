package br.com.jcomputacao.fwc.model;

import java.io.Serializable;

public class Rupd$Nova implements Serializable {

    private long id;
    private String dmltype$$;
    private long snapid;
    private byte[] changeVector$$;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDmltype$$() {
        return this.dmltype$$;
    }

    public void setDmltype$$(String dmltype$$) {
        this.dmltype$$ = dmltype$$;
    }

    public long getSnapid() {
        return this.snapid;
    }

    public void setSnapid(long snapid) {
        this.snapid = snapid;
    }

    public byte[] getChangeVector$$() {
        return this.changeVector$$;
    }

    public void setChangeVector$$(byte[] changeVector$$) {
        this.changeVector$$ = changeVector$$;
    }
}
