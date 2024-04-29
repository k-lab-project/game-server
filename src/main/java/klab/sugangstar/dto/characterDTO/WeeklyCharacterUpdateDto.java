package klab.sugangstar.dto.characterDTO;

import lombok.Data;
// 주차 업데이트
@Data
public class WeeklyCharacterUpdateDto {
    private Long characterId;
    private int week;
    private int stamina;
    private int health;
    private int debuff1;
    private int debuff2;
    private int debuff3;
    private float memorization;
    private float concentration;
    private float patience;
    private float creativity;
    private float metacognition;
    private float understanding;
}
