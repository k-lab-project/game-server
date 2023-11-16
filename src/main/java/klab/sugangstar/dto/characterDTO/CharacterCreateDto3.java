package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class CharacterCreateDto3 {
    private Long characterId;
    private int memorization;
    private int concentration;
    private int patience;
    private int creativity;
    private int metacognition;
    private int understanding;
    private List<Long> subjectIds;
}
