package klab.sugangstar.dto.characterDTO;

import lombok.Data;

import java.util.List;

@Data
public class CharacterCreateDto {
    private Long userId;
    private String nickName;
    private int memorization;
    private int concentration;
    private int patience;
    private int creativity;
    private int metacognition;
    private int understanding;
    private int semester;
    private List<Long> subjectIds;
}
