package klab.sugangstar.domain;

import klab.sugangstar.dto.characterDTO.WeeklyCharacterUpdateDto;
import klab.sugangstar.dto.characterDTO.EndOfSemesterCharacterUpdateDto;
import klab.sugangstar.dto.characterDTO.StartOfSemesterCharacterUpdateDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gameCharacter")
@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @OneToMany(mappedBy = "gameCharacter",cascade = CascadeType.ALL)
    private List<Status> status = new ArrayList<>();

    @Embedded
    private Debuff debuff;

    private int week;
    private int stamina;
    private int health;
    private int semester;
    private String nickName;
    private String gender;

    private float total_score;

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
                                                int health, Debuff debuff, int semester,String nickName, String gender,
                                                List<CharacterSubject> characterSubjects){
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setUser(user);
        for (CharacterSubject characterSubject : characterSubjects) {
            gameCharacter.addCharacterSubject(characterSubject);
        }
        gameCharacter.setNickName(nickName);
        gameCharacter.setDebuff(debuff);
        gameCharacter.setStamina(stamina);
        gameCharacter.setHealth(health);
        gameCharacter.setWeek(week);
        gameCharacter.setGender(gender);
        gameCharacter.setSemester(semester);
        gameCharacter.setTotal_score(0);


        return gameCharacter;
    }

    public void addStatus(Status status) {
        this.status.add(status);
        status.setGameCharacter(this);
    }
}
