package klab.sugangstar.controller;

import klab.sugangstar.domain.CharacterRanking;
import klab.sugangstar.dto.RankingDto;
import klab.sugangstar.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @PostMapping("/update-ranking")
    public CharacterRanking updateRanking(@RequestBody @Valid CharacterRanking characterRanking){
        rankingService.update(characterRanking);
        return characterRanking;
    }

    @GetMapping("/ranking")
    public List<CharacterRanking> getTop100Ranks() {
        return rankingService.getTop100Ranks();
    }
}
