package klab.sugangstar.domain;

import klab.sugangstar.dto.CharacterUpdateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="character")
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character {
    @Id
    @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    @Embedded
    private Status status;

    @Embedded
    private Debuff debuff;

    private int week;
    private int stamina;
    private int condition;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "character",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<CharacterSubject> characterSubjects = new ArrayList<>();

    // 연관 관계 메서드
    public void addCharacterSubject(CharacterSubject characterSubject){
        this.characterSubjects.add(characterSubject);
        characterSubject.setCharacter(this);
    }

    // 생성 메서드
    public static Character createCharacter(User user, int week, int stamina,
                                            int condition, Debuff debuff, Status status,
                                            List<CharacterSubject> characterSubjects){
        Character character = new Character();
        character.setUser(user);
        for (CharacterSubject characterSubject : characterSubjects) {
            character.addCharacterSubject(characterSubject);
        }
        character.setDebuff(debuff);
        character.setStamina(stamina);
        character.setCondition(condition);
        character.setWeek(week);
        character.setStatus(status);

        return character;
    }

    // 비즈니스 로직
    // 업데이트
    public void updateCharacter(CharacterUpdateDto characterUpdateDto){
        this.setWeek(characterUpdateDto.getWeek());
        this.setStamina(characterUpdateDto.getStamina());
        this.setCondition(characterUpdateDto.getCondition());
        this.setDebuff(new Debuff(characterUpdateDto.getDebuff1(),characterUpdateDto.getDebuff2(), characterUpdateDto.getDebuff3()));
        this.setStatus(new Status(characterUpdateDto.getMemorization(),characterUpdateDto.getConcentration(),characterUpdateDto.getPatience()
        ,characterUpdateDto.getCreativity(),characterUpdateDto.getMetacognition(),characterUpdateDto.getUnderstanding()));
    }
}
