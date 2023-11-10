package klab.sugangstar.repository;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {
    private final EntityManager em;

    // 저장
    public void save(Subject subject){
        em.persist(subject);
    }

    // 과목 정보 가져오기
    public List<SubjectProvideDto> findRandomSubjects(String name){
        List<SubjectProvideDto> subject = em.createQuery("SELECT new klab.sugangstar.dto.SubjectProvideDto(s.class_name, s.credit, s.korea_name, s.english_name, s.popularity, s.schedule_day, s.schedule_time, s.star, s.professor) " +
                        "FROM Subject s WHERE s.class_name = :class_name ORDER BY RAND()", SubjectProvideDto.class)
                .setParameter("class_name",name)
                .setMaxResults(10)
                .getResultList();
        return subject;
    }

    // 해당 과목 삭제, 한국어 이름으로 제거
    public void delete(Long id){
        Subject subject = em.find(Subject.class, id);
        if (subject != null) {
            // 엔티티 삭제
            em.remove(subject);
        } else {
            throw new EntityNotFoundException("Subject not found with ID: " + id);
        }
    }


}
