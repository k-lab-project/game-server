package klab.sugangstar.repository;

import klab.sugangstar.domain.CharacterRankingSql;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class CharacterRankSqlRepository {
    private final EntityManager em;

    // 랭킹 생성
    public void save(CharacterRankingSql rank){
        em.persist(rank);
    }

    // 전체 삭제
    public void deleteAll(){
        Query query = em.createQuery("DELETE FROM CharacterRankingSql");
        query.executeUpdate();
    }
}
