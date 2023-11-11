package klab.sugangstar.service;

import klab.sugangstar.domain.*;
import klab.sugangstar.domain.Character;
import klab.sugangstar.dto.CharacterCreateDto;
import klab.sugangstar.dto.CharacterUpdateDto;
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
        // 아마 이걸 개수만큼 해야할듯
        // 과목 생성
        // characterSubject에 과목들 넣어두고
        // addCharacterSubject로 넣어
        List<CharacterSubject> characterSubjects = new ArrayList<>();
        for (Long subjectId : characterCreateDto.getSubjectIds()) {
            Subject subject = subjectRepository.findById(subjectId);
            CharacterSubject characterSubject = CharacterSubject.createCharacterSubject(subject);
            characterSubjects.add(characterSubject);
        }

        // 디버프 세팅
        Debuff debuff = new Debuff(characterCreateDto.getDebuff1(),characterCreateDto.getDebuff2(),characterCreateDto.getDebuff3());

        // 스테이터스 세팅
        Status status = new Status(characterCreateDto.getMemorization(),characterCreateDto.getConcentration(),characterCreateDto.getPatience(),
                characterCreateDto.getCreativity(),characterCreateDto.getMetacognition(),characterCreateDto.getUnderstanding());

        // 캐릭터 생성
        // 캐릭터 생성함수로 캐릭터 생성하고
        Character character = Character.createCharacter(user,characterCreateDto.getWeek()
                ,characterCreateDto.getStamina(),characterCreateDto.getCondition(),debuff
                ,status,characterSubjects);

        // 캐릭터 저장
        characterRepository.save(character);
    }

    // 캐릭터 업데이트
    @Transactional
    public void updateCharacter(CharacterUpdateDto characterUpdateDto){
        Character character = characterRepository.findById(characterUpdateDto.getCharacterId());
        character.updateCharacter(characterUpdateDto);
    }

    // 캐릭터 조회
}
