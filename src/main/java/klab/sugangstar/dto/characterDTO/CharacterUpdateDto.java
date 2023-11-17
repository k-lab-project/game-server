package klab.sugangstar.dto.characterDTO;

import lombok.Data;
// 주차 업데이트
@Data
public class CharacterUpdateDto {
    private Long characterId;
    private int week;
    private int stamina;
    private int health;
    private int debuff1;
    private int debuff2;
    private int debuff3;
    private int memorization;
    private int concentration;
    private int patience;
    private int creativity;
    private int metacognition;
    private int understanding;
}
