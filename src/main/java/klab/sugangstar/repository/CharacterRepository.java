package klab.sugangstar.repository;

import klab.sugangstar.domain.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CharacterRepository {
    private final EntityManager em;

    // 캐릭터 생성
    public void save(GameCharacter gameCharacter){
        em.persist(gameCharacter);
    }

    // 캐릭터 정보 제공
    public GameCharacter findById(Long id){
        //Character character = em.find(Character.class,id);
        //System.out.println("character = " + character);
        GameCharacter gameCharacter = em.createQuery("select c from GameCharacter c " +
                "join fetch c.characterSubjects s join fetch c.user join fetch s.subject where c.id=:id", GameCharacter.class)
                .setParameter("id",id)
                .getSingleResult();
        return gameCharacter;
    }

    // 캐릭터 삭제
    public void deleteById(Long id){
        GameCharacter gameCharacter = em.find(GameCharacter.class,id);
        em.remove(gameCharacter);
    }

    // 캐릭터 업데이트, 이건 서비스 단에서 데이터 찾고 영속성 컨텍스트에서
    // 데이터를 변경되면 db에 커밋한다.

}
