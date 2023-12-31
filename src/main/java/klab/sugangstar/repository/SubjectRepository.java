package klab.sugangstar.repository;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
        List<SubjectProvideDto> subject1 = em.createQuery("SELECT new klab.sugangstar.dto.SubjectProvideDto(s.id,s.class_name, s.credit, s.korea_name, s.english_name, s.popularity, s.schedule_day, s.schedule_time, s.star, s.professor) " +
                        "FROM Subject s WHERE s.class_name = :class_name ORDER BY RAND()", SubjectProvideDto.class)
                .setParameter("class_name","M")
                .setMaxResults(14)
                .getResultList();
        List<SubjectProvideDto> subject2 = em.createQuery("SELECT new klab.sugangstar.dto.SubjectProvideDto(s.id,s.class_name, s.credit, s.korea_name, s.english_name, s.popularity, s.schedule_day, s.schedule_time, s.star, s.professor) " +
                        "FROM Subject s WHERE s.class_name = :class_name ORDER BY RAND()", SubjectProvideDto.class)
                .setParameter("class_name","G")
                .setMaxResults(14)
                .getResultList();
        List<SubjectProvideDto> subject3 = em.createQuery("SELECT new klab.sugangstar.dto.SubjectProvideDto(s.id,s.class_name, s.credit, s.korea_name, s.english_name, s.popularity, s.schedule_day, s.schedule_time, s.star, s.professor) " +
                        "FROM Subject s WHERE s.class_name = :class_name ORDER BY RAND()", SubjectProvideDto.class)
                .setParameter("class_name","N")
                .setMaxResults(2)
                .getResultList();
        subject.addAll(subject1);
        subject.addAll(subject2);
        subject.addAll(subject3);

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
