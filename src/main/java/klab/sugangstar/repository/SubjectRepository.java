package klab.sugangstar.repository;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {

    private final EntityManager em;

    Map<String, Integer> classNames = new HashMap<>(){{
        put("M", 14);
        put("G", 14);
        put("N", 2);
    }};

    // 저장
    public void save(Subject subject){
        em.persist(subject);
    }

    // 과목 정보 랜덤 가져오기
    public List<SubjectProvideDto> findRandomSubjects(){
        List<SubjectProvideDto> subject = new ArrayList<>();


        // 각 클래스 이름에 대해 쿼리 실행 및 결과 결합
        for (Map.Entry<String, Integer> entry : classNames.entrySet()) {
            String className = entry.getKey();
            int maxResults = entry.getValue();

            List<SubjectProvideDto> subjectsOfClass = em.createQuery(
                            "SELECT new klab.sugangstar.dto.SubjectProvideDto(s.id,s.class_name, s.credit, s.korea_name, s.english_name, s.popularity, s.schedule_day, s.schedule_time, s.star, s.professor) " +
                                    "FROM Subject s WHERE s.class_name = :class_name ORDER BY RAND()", SubjectProvideDto.class)
                    .setParameter("class_name", className)
                    .setMaxResults(maxResults)
                    .getResultList();

            if (subjectsOfClass.isEmpty()) {
                throw new NoResultException("No subjects found for class name: " + className);
            }

            subject.addAll(subjectsOfClass);
        }

        return subject;
    }
    // 아이디로 해당 과목만 가져오기
    public Subject findById(Long id){
        Subject subject = em.find(Subject.class,id);
        if (subject == null) {
            throw new EntityNotFoundException("Subject with id " + id + " not found");
        }
        return subject;
    }

    // 해당 과목 삭제, 한국어 이름으로 제거
    public void delete(Long id){
        Subject subject = em.find(Subject.class, id);
        if (subject != null) {
            // 엔티티 삭제
            em.remove(subject);
            return;
        }
        throw new EntityNotFoundException("Subject not found with ID: " + id);
    }


}
