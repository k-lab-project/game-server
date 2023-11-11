package klab.sugangstar.dto;

import lombok.Data;

@Data
public class CharacterUpdateDto {
    private Long characterId;
    private int week;
    private int stamina;
    private int condition;
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
