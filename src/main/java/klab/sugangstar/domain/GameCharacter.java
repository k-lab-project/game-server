package klab.sugangstar.domain;

import klab.sugangstar.dto.characterDTO.CharacterUpdateDto;
import klab.sugangstar.dto.characterDTO.CharacterUpdateDto2;
import klab.sugangstar.dto.characterDTO.CharacterCreateDto3;
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

    @OneToMany(mappedBy = "gameCharacter",cascade = CascadeType.ALL)
    private List<Status> status = new ArrayList<>();

    @Embedded
    private Debuff debuff;

    private int week;
    private int stamina;
    private int health;
    private int semester;
    private String nickName;

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
                                                int health, Debuff debuff, int semester,String nickName,
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
        gameCharacter.setSemester(semester);
        gameCharacter.setTotal_score(0);


        return gameCharacter;
    }

    public void addStatus(Status status) {
        this.status.add(status);
        status.setGameCharacter(this);
    }
    // 비즈니스 로직
    // 업데이트
    public void updateCharacter(CharacterUpdateDto characterUpdateDto){
        this.setWeek(characterUpdateDto.getWeek());
        this.setStamina(characterUpdateDto.getStamina());
        this.setHealth(characterUpdateDto.getHealth());
        this.setDebuff(new Debuff(characterUpdateDto.getDebuff1(),characterUpdateDto.getDebuff2(), characterUpdateDto.getDebuff3()));

        Status semesterStatus = this.status.get(this.semester - 1);
        semesterStatus.setMemorization(characterUpdateDto.getMemorization());
        semesterStatus.setConcentration(characterUpdateDto.getConcentration());
        semesterStatus.setUnderstanding(characterUpdateDto.getUnderstanding());
        semesterStatus.setMetacognition(characterUpdateDto.getMetacognition());
        semesterStatus.setPatience(characterUpdateDto.getPatience());
        semesterStatus.setCreativity(characterUpdateDto.getCreativity());
    }
    public void updateCharacter2(CharacterUpdateDto2 characterUpdateDto2){
        this.setWeek(characterUpdateDto2.getWeek());
        this.setStamina(characterUpdateDto2.getStamina());
        this.setHealth(characterUpdateDto2.getHealth());
        this.setTotal_score(characterUpdateDto2.getTotal_score());
        this.setDebuff(new Debuff(characterUpdateDto2.getDebuff1(),characterUpdateDto2.getDebuff2(), characterUpdateDto2.getDebuff3()));

        Status semesterStatus = this.status.get(this.semester - 1);
        semesterStatus.setMemorization(characterUpdateDto2.getMemorization());
        semesterStatus.setConcentration(characterUpdateDto2.getConcentration());
        semesterStatus.setUnderstanding(characterUpdateDto2.getUnderstanding());
        semesterStatus.setMetacognition(characterUpdateDto2.getMetacognition());
        semesterStatus.setPatience(characterUpdateDto2.getPatience());
        semesterStatus.setCreativity(characterUpdateDto2.getCreativity());

        List<CharacterSubject> characterSubjects1 = this.characterSubjects;
        int i=0;
        for (CharacterSubject characterSubject : characterSubjects1) {
            if(characterSubject.getSemester() == this.getSemester()){
                characterSubject.setScore(characterUpdateDto2.getSubjectScore().get(i));
                i++;
            }
        }
    }

    public void updateCharacter3(CharacterCreateDto3 characterUpdateDto3,List<CharacterSubject> characterSubjects){
        this.setSemester(2);
        Status semesterStatus = this.status.get(this.semester - 1);
        semesterStatus.setMemorization(characterUpdateDto3.getMemorization());
        semesterStatus.setConcentration(characterUpdateDto3.getConcentration());
        semesterStatus.setUnderstanding(characterUpdateDto3.getUnderstanding());
        semesterStatus.setMetacognition(characterUpdateDto3.getMetacognition());
        semesterStatus.setPatience(characterUpdateDto3.getPatience());
        semesterStatus.setCreativity(characterUpdateDto3.getCreativity());
        for (CharacterSubject characterSubject : characterSubjects) {
            this.addCharacterSubject(characterSubject);
        }
        this.setDebuff(new Debuff(0,0,0));
        this.setStamina(100);
        this.setHealth(4);
        this.setWeek(1);
        this.setTotal_score(0);
    }

}
