package klab.sugangstar.service;

import klab.sugangstar.domain.*;
import klab.sugangstar.domain.GameCharacter;
import klab.sugangstar.dto.characterDTO.*;
import klab.sugangstar.repository.CharacterRepository;
import klab.sugangstar.repository.SubjectRepository;
import klab.sugangstar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    // 캐릭터 생성
    @Transactional
    public void createCharacter(CharacterCreateDto characterCreateDto){
        // 엔티티 조회
        // userId 조회
        User user = userRepository.findOne(characterCreateDto.getUserId());


        // 디버프 세팅
        Debuff debuff = new Debuff(0,0,0);

        Status status = Status.createStatus(characterCreateDto.getMemorization(),characterCreateDto.getConcentration(),characterCreateDto.getPatience(),
                characterCreateDto.getCreativity(),characterCreateDto.getMetacognition(),characterCreateDto.getUnderstanding(),characterCreateDto.getSemester());


        List<CharacterSubject> characterSubjects = new ArrayList<>();
        for (Long subjectId : characterCreateDto.getSubjectIds()) {
            Subject subject = subjectRepository.findById(subjectId);
            CharacterSubject characterSubject = CharacterSubject.createCharacterSubject(subject,characterCreateDto.getSemester());
            characterSubjects.add(characterSubject);
        }


        GameCharacter gameCharacter = GameCharacter.createCharacter(user, 1,
                100, 4, debuff, 1,characterCreateDto.getNickName(),characterCreateDto.getGender(),
                characterSubjects);

        gameCharacter.addStatus(status);
        // 스테이터스 세팅


        // 캐릭터 저장
        characterRepository.save(gameCharacter);
    }

    // 캐릭터 업데이트, 학기 중 업데이트, week , updateWeekly
    @Transactional
    public void updateWeekly(WeeklyCharacterUpdateDto dto){
        GameCharacter gameCharacter = characterRepository.findById(dto.getCharacterId());

        gameCharacter.setWeek(dto.getWeek());
        gameCharacter.setStamina(dto.getStamina());
        gameCharacter.setHealth(dto.getHealth());
        gameCharacter.setDebuff(new Debuff(dto.getDebuff1(), dto.getDebuff2(), dto.getDebuff3()));

        Status semesterStatus = gameCharacter.getStatus().get(gameCharacter.getSemester() - 1);
        semesterStatus.setMemorization(dto.getMemorization());
        semesterStatus.setConcentration(dto.getConcentration());
        semesterStatus.setUnderstanding(dto.getUnderstanding());
        semesterStatus.setMetacognition(dto.getMetacognition());
        semesterStatus.setPatience(dto.getPatience());
        semesterStatus.setCreativity(dto.getCreativity());

        characterRepository.save(gameCharacter);
    }

    // 캐릭터 업데이트, 학기 종료 시, 시험 종료 시 updateAfterSemesterEnd
    @Transactional
    public void updateAfterSemesterEnd(EndOfSemesterCharacterUpdateDto dto){
        GameCharacter gameCharacter = characterRepository.findById(dto.getCharacterId());

        gameCharacter.setWeek(dto.getWeek());
        gameCharacter.setStamina(dto.getStamina());
        gameCharacter.setHealth(dto.getHealth());
        gameCharacter.setTotal_score(dto.getTotal_score());
        gameCharacter.setDebuff(new Debuff(dto.getDebuff1(), dto.getDebuff2(), dto.getDebuff3()));

        Status semesterStatus = gameCharacter.getStatus().get(gameCharacter.getSemester() - 1);
        semesterStatus.setMemorization(dto.getMemorization());
        semesterStatus.setConcentration(dto.getConcentration());
        semesterStatus.setUnderstanding(dto.getUnderstanding());
        semesterStatus.setMetacognition(dto.getMetacognition());
        semesterStatus.setPatience(dto.getPatience());
        semesterStatus.setCreativity(dto.getCreativity());

        List<CharacterSubject> characterSubjects = gameCharacter.getCharacterSubjects();
        int i = 0;
        for (CharacterSubject characterSubject : characterSubjects) {
            if (characterSubject.getSemester() == gameCharacter.getSemester()) {
                characterSubject.setScore(dto.getSubjectScore().get(i));
                i++;
            }
        }
    }


    //캐릭터 업데이트. 새 학기 시작 updateAtSemesterStart
    @Transactional
    public void updateAtSemesterStart(StartOfSemesterCharacterUpdateDto startOfSemesterCharacterUpdateDto){
        GameCharacter gameCharacter = characterRepository.findById(startOfSemesterCharacterUpdateDto.getCharacterId());

        gameCharacter.setSemester(2);
        Status semesterStatus = gameCharacter.getStatus().get(1);
        semesterStatus.setMemorization(startOfSemesterCharacterUpdateDto.getMemorization());
        semesterStatus.setConcentration(startOfSemesterCharacterUpdateDto.getConcentration());
        semesterStatus.setUnderstanding(startOfSemesterCharacterUpdateDto.getUnderstanding());
        semesterStatus.setMetacognition(startOfSemesterCharacterUpdateDto.getMetacognition());
        semesterStatus.setPatience(startOfSemesterCharacterUpdateDto.getPatience());
        semesterStatus.setCreativity(startOfSemesterCharacterUpdateDto.getCreativity());

        List<CharacterSubject> characterSubjects = new ArrayList<>();
        for (Long subjectId : startOfSemesterCharacterUpdateDto.getSubjectIds()) {
            Subject subject = subjectRepository.findById(subjectId);
            CharacterSubject characterSubject = CharacterSubject.createCharacterSubject(subject, 2);
            characterSubjects.add(characterSubject);
        }
        gameCharacter.setCharacterSubjects(characterSubjects);
        gameCharacter.setDebuff(new Debuff(0, 0, 0));
        gameCharacter.setStamina(100);
        gameCharacter.setHealth(4);
        gameCharacter.setWeek(1);
        gameCharacter.setTotal_score(0);
    }


    // 캐릭터 조회
    public CharacterProvideDto provideCharacter(Long id){
        GameCharacter gameCharacter = characterRepository.findById(id);
        List<CharacterSubject> subjects = new ArrayList<>();
        for (CharacterSubject characterSubject : gameCharacter.getCharacterSubjects()) {
            if(gameCharacter.getSemester() == characterSubject.getSemester()){
                subjects.add(characterSubject);
            }

        }
        CharacterProvideDto characterProvideDto = CharacterProvideDto.builder()
                .characterId(gameCharacter.getId())
                .week(gameCharacter.getWeek())
                .stamina(gameCharacter.getStamina())
                .health(gameCharacter.getHealth())
                .semester(gameCharacter.getSemester())
                .total_score(gameCharacter.getTotal_score())
                .nickName(gameCharacter.getNickName())
                .gender(gameCharacter.getGender())
                .debuff(gameCharacter.getDebuff())
                .status(gameCharacter.getStatus().get(gameCharacter.getSemester()-1))
                .subjects(subjects)
                .build();
        return characterProvideDto;
    }

    // 캐릭터 삭제
    @Transactional
    public void deleteCharacter(Long id){
        characterRepository.deleteById(id);
    }


}
