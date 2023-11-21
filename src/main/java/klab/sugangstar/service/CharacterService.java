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
                100, 4, debuff, 1,characterCreateDto.getNickName(),
                characterSubjects);

        gameCharacter.addStatus(status);
        // 스테이터스 세팅


        // 캐릭터 저장
        characterRepository.save(gameCharacter);
    }

    // 캐릭터 업데이트, 학기 중 업데이트, week
    @Transactional
    public void updateCharacter(CharacterUpdateDto characterUpdateDto){
        GameCharacter gameCharacter = characterRepository.findById(characterUpdateDto.getCharacterId());
        gameCharacter.updateCharacter(characterUpdateDto);
    }

    // 캐릭터 업데이트, 학기 종료 시, 시험 종료 시
    @Transactional
    public void updateCharacter2(CharacterUpdateDto2 characterUpdateDto2){
        GameCharacter gameCharacter = characterRepository.findById(characterUpdateDto2.getCharacterId());
        gameCharacter.updateCharacter2(characterUpdateDto2);
    }

    //캐릭터 업데이트. 새 학기 시작
    @Transactional
    public void updateCharacter3(CharacterCreateDto3 characterUpdateDto3){
        GameCharacter gameCharacter = characterRepository.findById(characterUpdateDto3.getCharacterId());
        List<CharacterSubject> characterSubjects = new ArrayList<>();
        for (Long subjectId : characterUpdateDto3.getSubjectIds()) {
            Subject subject = subjectRepository.findById(subjectId);
            CharacterSubject characterSubject = CharacterSubject.createCharacterSubject(subject,2);
            characterSubjects.add(characterSubject);
        }
        gameCharacter.updateCharacter3(characterUpdateDto3,characterSubjects);
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
        CharacterProvideDto characterProvideDto = new CharacterProvideDto(gameCharacter.getId(), gameCharacter.getWeek(), gameCharacter.getStamina()
        , gameCharacter.getHealth(), gameCharacter.getSemester(),gameCharacter.getTotal_score(),gameCharacter.getNickName(),gameCharacter.getDebuff()
                , gameCharacter.getStatus().get(gameCharacter.getSemester()-1), subjects);
        return characterProvideDto;
    }

    // 캐릭터 삭제
    @Transactional
    public void deleteCharacter(Long id){
        characterRepository.deleteById(id);
    }


}
