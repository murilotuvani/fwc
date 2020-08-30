package br.com.jcomputacao.fwc.model;

import java.io.Serializable;
import java.util.Date;

public class Mlog$TesteDois implements Serializable {

    private String mRow$$;
    private Date snaptime$$;
    private String dmltype$$;
    private String oldNew$$;
    private byte[] changeVector$$;

    public String getMRow$$() {
        return this.mRow$$;
    }

    public void setMRow$$(String mRow$$) {
        this.mRow$$ = mRow$$;
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
