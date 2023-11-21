package klab.sugangstar.repository;

import klab.sugangstar.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    // 저장
    public Long save(User user){
        em.persist(user);
        return user.getId();
    }

    // 삭제

    // 업데이트

    // 유저 찾기
    public User findOne(Long id){
        return em.find(User.class,id);
    }

    public Long findByUsernameAndPassword(String id, String password){
        List<User> user  =em.createQuery("SELECT u FROM User u WHERE u.login_id = :login_id AND u.login_password = :login_password",User.class)
                    .setParameter("login_id",id)
                    .setParameter("login_password",password)
                    .getResultList();
        if (!user.isEmpty()) {
            // 여러 개의 결과 중 하나를 선택하거나 처리
            return user.get(0).getId();
        } else {
            return (long) -1;
        }

    }
}
