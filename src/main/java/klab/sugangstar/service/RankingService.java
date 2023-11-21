package klab.sugangstar.service;

import klab.sugangstar.domain.CharacterRanking;
import klab.sugangstar.repository.CharacterRankRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private CharacterRankRepository characterRankRepository;
    @Autowired
    public RankingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void updateRanking(CharacterRanking characterRanking){
        rabbitTemplate.convertAndSend("ranking.exchange","ranking.key",characterRanking);
        //characterRankRepository.deleteAll();
    }

    public List<CharacterRanking> getTop100Ranks() {
        try{
            List<CharacterRanking> test = characterRankRepository.findByOrderByTotalGradeDescTotalScoreDesc();//.stream()
            return test;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

//                .limit(100)
//                .collect(Collectors.toList());
       // return characterRankRepository.findAll();
    }
}
