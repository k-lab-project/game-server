package klab.sugangstar.domain;

import klab.sugangstar.dto.CharacterUpdateDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gameCharacter")
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @Embedded
    private Status status;

    @Embedded
    private Debuff debuff;

    private int week;
    private int stamina;
    private int health;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "gameCharacter",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<CharacterSubject> characterSubjects = new ArrayList<>();

    // 연관 관계 메서드
    public void addCharacterSubject(CharacterSubject characterSubject){
        this.characterSubjects.add(characterSubject);
        characterSubject.setGameCharacter(this);
    }

    // 생성 메서드
    public static GameCharacter createCharacter(User user, int week, int stamina,
                                                int health, Debuff debuff, Status status,
                                                List<CharacterSubject> characterSubjects){
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setUser(user);
        for (CharacterSubject characterSubject : characterSubjects) {
            gameCharacter.addCharacterSubject(characterSubject);
        }
        gameCharacter.setDebuff(debuff);
        gameCharacter.setStamina(stamina);
        gameCharacter.setHealth(health);
        gameCharacter.setWeek(week);
        gameCharacter.setStatus(status);

        return gameCharacter;
    }

    // 비즈니스 로직
    // 업데이트
    public void updateCharacter(CharacterUpdateDto characterUpdateDto){
        this.setWeek(characterUpdateDto.getWeek());
        this.setStamina(characterUpdateDto.getStamina());
        this.setHealth(characterUpdateDto.getHealth());
        this.setDebuff(new Debuff(characterUpdateDto.getDebuff1(),characterUpdateDto.getDebuff2(), characterUpdateDto.getDebuff3()));
        this.setStatus(new Status(characterUpdateDto.getMemorization(),characterUpdateDto.getConcentration(),characterUpdateDto.getPatience()
        ,characterUpdateDto.getCreativity(),characterUpdateDto.getMetacognition(),characterUpdateDto.getUnderstanding()));
    }
}
