package klab.sugangstar.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="characterRankingSql")
@Getter
@Setter
public class CharacterRankingSql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ranking_id")
    private Long id;

    private Double totalScore;
    private Double totalGrade;
    private String nickname;
}
