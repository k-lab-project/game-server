package klab.sugangstar.service;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import klab.sugangstar.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    // 과목 저장
    @Transactional
    public void createSubject(SubjectCreateDto subject){
        // validation 해야함, 지금은 보류, 여기서 모든 값들이 not empty이기에 그것에 대해 처리해야함.
        Subject subject1 = Subject.fromDto(subject);
        subjectRepository.save(subject1);
    }
    // 과목 정보 가져오기
    public List<List<String>> findRandom(){
        List<SubjectProvideDto> subjects = subjectRepository.findRandomSubjects();
        return subjects.stream()
                .map(subject -> Arrays.asList(
                        String.valueOf(subject.getId()),
                        subject.getClass_name(),
                        subject.getCredit(),
                        subject.getKorea_name(),
                        subject.getEnglish_name(),
                        subject.getPopularity(),
                        subject.getSchedule_day(),
                        subject.getSchedule_time(),
                        subject.getStar(),
                        subject.getProfessor()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        subjectRepository.delete(id);
    }

}
