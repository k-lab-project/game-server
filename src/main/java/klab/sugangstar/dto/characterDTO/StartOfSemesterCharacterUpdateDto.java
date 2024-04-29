package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class StartOfSemesterCharacterUpdateDto {
    private Long characterId;
    private float memorization;
    private float concentration;
    private float patience;
    private float creativity;
    private float metacognition;
    private float understanding;
    private List<Long> subjectIds;
}
