package klab.sugangstar.repository;

import klab.sugangstar.domain.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CharacterRepository {
    private final EntityManager em;

    // 캐릭터 생성
    public void save(Character character){
        em.persist(character);
    }

    // 캐릭터 정보 제공
    public Character findById(Long id){
        return em.find(Character.class,id);
    }

    // 캐릭터 삭제


    // 캐릭터 업데이트, 이건 서비스 단에서 데이터 찾고 영속성 컨텍스트에서
    // 데이터를 변경되면 db에 커밋한다.

}
