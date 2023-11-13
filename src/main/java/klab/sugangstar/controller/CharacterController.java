package klab.sugangstar.controller;

import klab.sugangstar.dto.CharacterCreateDto;
import klab.sugangstar.dto.CharacterProvideDto;
import klab.sugangstar.dto.CharacterUpdateDto;
import klab.sugangstar.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    // 캐릭터 생성
    @PostMapping("/create-character")
    public void saveCharacter(@RequestBody @Valid CharacterCreateDto characterCreateDto){
        characterService.createCharacter(characterCreateDto);
        //return character;
    }

    // 캐릭터 업데이트
    @PutMapping("/update-character")
    public void updateCharacter(@RequestBody @Valid CharacterUpdateDto characterUpdateDto){
        characterService.updateCharacter(characterUpdateDto);
    }

    // 캐릭터 정보 제공
    @GetMapping("/character/{id}")
    public CharacterProvideDto provideCharacter(@PathVariable Long id){
        CharacterProvideDto character = characterService.provideCharacter(id);
        System.out.println("character = " + character);
        return character;
    }

    // 캐릭터 삭제
    @DeleteMapping("/delete-character/{id}")
    public Long deleteCharacter(@PathVariable Long id){
        characterService.deleteCharacter(id);
        return id;
    }
}
