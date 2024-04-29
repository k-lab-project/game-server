package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class EndOfSemesterCharacterUpdateDto {
    private Long characterId;
    private int week;
    private int stamina;
    private int health;
    private float total_score;
    private int debuff1;
    private int debuff2;
    private int debuff3;
    private List<Float> subjectScore;
    private float memorization;
    private float concentration;
    private float patience;
    private float creativity;
    private float metacognition;
    private float understanding;
}
