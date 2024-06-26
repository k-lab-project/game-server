package klab.sugangstar.controller;

import klab.sugangstar.dto.characterDTO.*;
import klab.sugangstar.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    // 캐릭터 생성
    @PostMapping("/characters")
    public void saveCharacter(@RequestBody @Valid CharacterCreateDto characterCreateDto){
        // 또 요청하면 아예 오류 뜨게 해서 validation 처리
        characterService.createCharacter(characterCreateDto);
        //return character;
    }

    // 캐릭터 주차별 업데이트
    @PutMapping("/characters/weekly")
    public void weeklyCharacterUpdate(@RequestBody @Valid WeeklyCharacterUpdateDto weeklyCharacterUpdateDto){
        characterService.updateWeekly(weeklyCharacterUpdateDto);
    }

    // 캐릭터 업데이트2, 시험 종료 시점
    @PutMapping("/characters/end-of-semester")
    public void endOfSemesterCharacterUpdate(@RequestBody @Valid EndOfSemesterCharacterUpdateDto endOfSemesterCharacterUpdateDto){
        characterService.updateAfterSemesterEnd(endOfSemesterCharacterUpdateDto);
    }

    //캐릭터 업데이트. 새 학기 시작
    @PutMapping(" /characters/start-of-semester")
    public void startOfSemesterCharacterUpdate(@RequestBody @Valid StartOfSemesterCharacterUpdateDto startOfSemesterCharacterUpdateDto){
        characterService.updateAtSemesterStart(startOfSemesterCharacterUpdateDto);
    }

    // 캐릭터 정보 제공
    @GetMapping("/characters/{id}")
    public CharacterProvideDto getCharacter(@PathVariable Long id){
        CharacterProvideDto character = characterService.provideCharacter(id);
        System.out.println("character = " + character);
        return character;
    }

    // 캐릭터 삭제
    @DeleteMapping("/characters/{id}")
    public Long deleteCharacter(@PathVariable Long id){
        characterService.deleteCharacter(id);
        return id;
    }
}
