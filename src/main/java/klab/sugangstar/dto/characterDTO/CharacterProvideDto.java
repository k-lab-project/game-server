package klab.sugangstar.dto.characterDTO;

import klab.sugangstar.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CharacterProvideDto {
    private Long characterId;
    private int week;
    private int stamina;
    private int health;
    private int semester;
    private float total_score;
    private String nickName;
    private String gender;
    private Debuff debuff;
    private Status status;
    private List<CharacterSubject> subjects;

    // 정적 팩토리 메서드로 dto 생성
    public CharacterProvideDto toDto(GameCharacter gameCharacter){
        return CharacterProvideDto.builder().build();
    }
}
