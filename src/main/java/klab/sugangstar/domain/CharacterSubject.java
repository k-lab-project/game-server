package klab.sugangstar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CharacterSubject {
    @Id
    @GeneratedValue
    @Column(name = "character_subject_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private Character character;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private Subject subject;

    // 생성 메서드
    public static CharacterSubject createCharacterSubject(Subject subject){
        CharacterSubject characterSubject = new CharacterSubject();
        characterSubject.setSubject(subject);
        return characterSubject;
    }
}
