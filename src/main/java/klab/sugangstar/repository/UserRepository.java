package klab.sugangstar.repository;

import klab.sugangstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    // 저장
    public void save(User user){
        em.persist(user);
    }

    // 삭제

    // 업데이트

    // 유저 찾기
    public User findOne(Long id){
        return em.find(User.class,id);
    }
}
