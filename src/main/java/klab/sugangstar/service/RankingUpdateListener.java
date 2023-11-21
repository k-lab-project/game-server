package klab.sugangstar.service;

import klab.sugangstar.domain.CharacterRanking;
import klab.sugangstar.repository.CharacterRankRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RankingUpdateListener {
    @Autowired
    private CharacterRankRepository characterRankRepository;
    @RabbitListener(queues = "ranking.queue")
    public void processRankingUpdate(CharacterRanking characterRanking) {
        try {
            characterRankRepository.save(characterRanking);
            //characterRankRepository.deleteAllExceptTop100();
            // RabbitMQ에서 받은 메시지를 처리하여 랭킹 업데이트
            System.out.println(characterRanking);
            // 여기서 랭킹 업데이트 로직을 구현
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.out.println(e);;
        }
    }
}
