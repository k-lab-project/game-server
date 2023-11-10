package klab.sugangstar.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Debuff {
    private String debuff1;
    private String debuff2;
    private String debuff3;

    protected Debuff(){}

    public Debuff(String debuff1, String debuff2, String debuff3) {
        this.debuff1 = debuff1;
        this.debuff2 = debuff2;
        this.debuff3 = debuff3;
    }
}
