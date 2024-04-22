package klab.sugangstar.repository;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {
    private final EntityManager em;

    // 저장
    public void save(Subject subject){
        em.persist(subject);
    }

    // 과목 정보 랜덤 가져오기
    public List<SubjectProvideDto> findRandomSubjects(){
        List<SubjectProvideDto> subject = new ArrayList<>();

        // 클래스 이름 리스트
        List<String> classNames = Arrays.asList("M", "G", "N");

        // 각 클래스 이름에 대해 쿼리 실행 및 결과 결합
        for (String className : classNames) {
            List<SubjectProvideDto> subjectsOfClass = em.createQuery(
                            "SELECT new klab.sugangstar.dto.SubjectProvideDto(s.id,s.class_name, s.credit, s.korea_name, s.english_name, s.popularity, s.schedule_day, s.schedule_time, s.star, s.professor) " +
                                    "FROM Subject s WHERE s.class_name = :class_name ORDER BY RAND()", SubjectProvideDto.class)
                    .setParameter("class_name", className)
                    .setMaxResults(className.equals("N") ? 2 : 14) // 클래스 이름이 "N"이면 최대 결과 수를 2로 설정
                    .getResultList();
            subject.addAll(subjectsOfClass);
        }

        return subject;
    }
    // 아이디로 해당 과목만 가져오기
    public Subject findById(Long id){
        Subject subject = em.find(Subject.class,id);
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
