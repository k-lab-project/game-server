package klab.sugangstar.dto;

import klab.sugangstar.domain.CharacterSubject;
import klab.sugangstar.domain.Debuff;
import klab.sugangstar.domain.Status;
import klab.sugangstar.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CharacterProvideDto {
    private Long characterId;
    private int week;
    private int stamina;
    private int condition;
    private Debuff debuff;
    private Status status;
    private List<CharacterSubject> subjects;
}
