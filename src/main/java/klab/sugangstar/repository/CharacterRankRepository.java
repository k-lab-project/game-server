package klab.sugangstar.repository;

import klab.sugangstar.domain.CharacterRanking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharacterRankRepository extends CrudRepository<CharacterRanking, String> {
    //@Query("SELECT c FROM CharacterRanking c ORDER BY c.totalGrade DESC")
    List<CharacterRanking> findByOrderByTotalGradeDescTotalScoreDesc();


    // Redis에서 일부 데이터 삭제 (버림)
//    @Query("DELETE FROM CharacterRanking c WHERE c.id NOT IN (SELECT cr.id FROM CharacterRanking cr ORDER BY cr.totalGrade DESC, cr.totalScore DESC LIMIT 100)")
//    void deleteAllExceptTop100();

    List<CharacterRanking> findAll();
    CharacterRanking save(CharacterRanking characterRank);
}
