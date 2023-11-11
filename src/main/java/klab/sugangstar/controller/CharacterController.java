package klab.sugangstar.controller;

import klab.sugangstar.domain.Character;
import klab.sugangstar.dto.CharacterCreateDto;
import klab.sugangstar.dto.CharacterUpdateDto;
import klab.sugangstar.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
