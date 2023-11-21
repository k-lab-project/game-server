package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class CharacterCreateDto {
    private Long userId;
    private String nickName;
    private float memorization;
    private float concentration;
    private float patience;
    private float creativity;
    private float metacognition;
    private float understanding;
    private int semester;
    private List<Long> subjectIds;
}
