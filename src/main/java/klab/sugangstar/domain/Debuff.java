package klab.sugangstar.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Debuff {
    private int debuff1;
    private int debuff2;
    private int debuff3;

    protected Debuff(){}

    public Debuff(int debuff1, int debuff2, int debuff3) {
        this.debuff1 = debuff1;
        this.debuff2 = debuff2;
        this.debuff3 = debuff3;
    }
}
