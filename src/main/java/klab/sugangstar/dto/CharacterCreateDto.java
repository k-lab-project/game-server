package klab.sugangstar.dto;

import klab.sugangstar.domain.*;
import klab.sugangstar.domain.Character;
import klab.sugangstar.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class CharacterCreateDto {
    private Long userId;
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
    private List<Long> subjectIds;
}
