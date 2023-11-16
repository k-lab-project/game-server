package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class CharacterUpdateDto2 {
    private Long characterId;
    private int week;
    private int stamina;
    private int health;
    private List<Integer> score;
    private int total_score;
    private String debuff1;
    private String debuff2;
    private String debuff3;
    private int memorization;
    private int concentration;
    private int patience;
    private int creativity;
    private int metacognition;
    private int understanding;
}
