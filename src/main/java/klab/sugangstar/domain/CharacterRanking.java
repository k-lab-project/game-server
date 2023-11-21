package klab.sugangstar.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

import javax.persistence.Id;
import java.util.List;

@RedisHash("CharacterRanking")
@Getter
@Setter
public class CharacterRanking {
    @Id
    private String id;

    private Double totalScore;
    private Double totalGrade;
    private String nickname;
    private List<Float> grade1;
    private List<Float> grade2;
    private List<Float> status1;
    private List<Float> status2;
    private List<String> subjectName1;
    private List<String> subjectName2;

}
