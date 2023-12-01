package klab.sugangstar.service;

import klab.sugangstar.domain.CharacterRanking;
import klab.sugangstar.domain.CharacterRankingSql;
import klab.sugangstar.repository.CharacterRankRepository;
import klab.sugangstar.repository.CharacterRankSqlRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RankingService {
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private CharacterRankRepository characterRankRepository;

    @Autowired
    private CharacterRankSqlRepository  characterRankSqlRepository;
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
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //  mysql 영속적으로 스케줄링하여 저장(현재는 1분 단위로 저장)
    @Transactional
    @Scheduled(fixedRate = 60000)
    public void updateRankingDataInMySQL() {
        // Redis에서 랭킹 데이터를 읽어옴
        List<CharacterRanking> redisRankingDataList = getTop100Ranks();

        // MySQL에서 데이터를 전부 삭제
        characterRankSqlRepository.deleteAll();

        // Mysql에 다시 데이터 추가
        for (CharacterRanking characterRanking : redisRankingDataList) {
            CharacterRankingSql c = new CharacterRankingSql();
            c.setNickname(characterRanking.getNickname());
            c.setTotalScore(characterRanking.getTotalScore());
            c.setTotalGrade(characterRanking.getTotalGrade());
            characterRankSqlRepository.save(c);

        }
    }

}
