package klab.sugangstar.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Status {
    private int memorization;
    private int concentration;
    private int patience;
    private int creativity;
    private int metacognition;
    private int understanding;

    protected Status(){}

    public Status(int memorization, int concentration, int patience, int creativity, int metacognition, int understanding) {
        this.memorization = memorization;
        this.concentration = concentration;
        this.patience = patience;
        this.creativity = creativity;
        this.metacognition = metacognition;
        this.understanding = understanding;
    }
}
