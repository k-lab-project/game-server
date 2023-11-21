package klab.sugangstar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="characterSubject")
@Getter
@Setter
public class CharacterSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_subject_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private GameCharacter gameCharacter;

    private int semester;
    private float score;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private Subject subject;


    // 생성 메서드
    public static CharacterSubject createCharacterSubject(Subject subject,int semester){
        CharacterSubject characterSubject = new CharacterSubject();
        characterSubject.setSubject(subject);
        characterSubject.setSemester(semester);
        characterSubject.setScore(0);
        return characterSubject;
    }

    // 로직 메서드
    // 점수 변경
    public void updateScore(int score){
        this.score = score;
    }
}
