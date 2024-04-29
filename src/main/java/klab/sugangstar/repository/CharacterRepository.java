package klab.sugangstar.repository;

import klab.sugangstar.domain.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CharacterRepository {
    private final EntityManager em;

    // 캐릭터 생성
    public void save(GameCharacter gameCharacter){
        em.persist(gameCharacter);
    }

    // 캐릭터 정보 제공
    public GameCharacter findById(Long id) throws NoResultException {
        return em.createQuery("select c from GameCharacter c " +
                        "join fetch c.characterSubjects s join fetch c.user join fetch s.subject where c.id=:id", GameCharacter.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // 캐릭터 삭제
    public void deleteById(Long id){
        GameCharacter gameCharacter = em.find(GameCharacter.class,id);
        em.remove(gameCharacter);
    }
    public Long findByUserId(Long id){
        Long result = 0L;
        List<Long> count = em.createQuery("SELECT c.id FROM GameCharacter c " +
                "WHERE c.user.id = :userId",Long.class)
                .setParameter("userId",id)
                .getResultList();
        if (!count.isEmpty()) {
            result = count.get(0);
        }
        return result;
    }

    // 캐릭터 업데이트, 이건 서비스 단에서 데이터 찾고 영속성 컨텍스트에서
    // 데이터를 변경되면 db에 커밋한다.

}
