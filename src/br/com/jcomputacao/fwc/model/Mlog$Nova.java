package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Mlog$Nova implements Serializable {

    private long id;
    private Date snaptime$$;
    private String dmltype$$;
    private String oldNew$$;
    private byte[] changeVector$$;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSnaptime$$() {
        return this.snaptime$$;
    }

    public void setSnaptime$$(Date snaptime$$) {
        this.snaptime$$ = snaptime$$;
    }

    public String getDmltype$$() {
        return this.dmltype$$;
    }

    public void setDmltype$$(String dmltype$$) {
        this.dmltype$$ = dmltype$$;
    }

    public String getOldNew$$() {
        return this.oldNew$$;
    }

    public void setOldNew$$(String oldNew$$) {
        this.oldNew$$ = oldNew$$;
    }

    public byte[] getChangeVector$$() {
        return this.changeVector$$;
    }

    public void setChangeVector$$(byte[] changeVector$$) {
        this.changeVector$$ = changeVector$$;
    }
}
