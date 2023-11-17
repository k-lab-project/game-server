package klab.sugangstar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="status")
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;

    private int memorization;
    private int concentration;
    private int patience;
    private int creativity;
    private int metacognition;
    private int understanding;
    private int semester;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private GameCharacter gameCharacter;

    public static Status createStatus(int memorization,int concentration, int patience,
                                      int creativity,int metacognition, int understanding, int semester) {
        Status status = new Status();
        status.setMemorization(memorization);
        status.setCreativity(creativity);
        status.setConcentration(concentration);
        status.setPatience(patience);
        status.setMetacognition(metacognition);
        status.setUnderstanding(understanding);
        status.setSemester(semester);
        return status;
    }
}
