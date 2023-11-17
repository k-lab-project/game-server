package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class CharacterUpdateDto2 {
    private Long characterId;
    private int week;
    private int stamina;
    private int health;
    private int total_score;
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
