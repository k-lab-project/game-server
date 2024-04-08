package klab.sugangstar.domain;

import klab.sugangstar.dto.SubjectCreateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@Table(name="subject")
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    private String class_name; // M, G, N
    private String credit;
    private String korea_name;
    private String english_name;
    private String popularity;
    private String schedule_day;
    private String schedule_time;

    @Builder
    public Subject(Long id, String class_name, String credit, String korea_name, String english_name, String popularity, String schedule_day, String schedule_time, String star, String professor, String status1, String status2) {
        this.id = id;
        this.class_name = class_name;
        this.credit = credit;
        this.korea_name = korea_name;
        this.english_name = english_name;
        this.popularity = popularity;
        this.schedule_day = schedule_day;
        this.schedule_time = schedule_time;
        this.star = star;
        this.professor = professor;
        this.status1 = status1;
        this.status2 = status2;
    }

    private String star;
    private String professor;
    // 해당 과목이 요구하는 능력치
    private String status1;
    private String status2;


    // 생성 메서드
    public static Subject fromDto(SubjectCreateDto dto) {
        return Subject.builder()
                .class_name(dto.getClass_name())
                .credit(dto.getCredit())
                .korea_name(dto.getKorea_name())
                .english_name(dto.getEnglish_name())
                .popularity(dto.getPopularity())
                .schedule_day(dto.getSchedule_day())
                .schedule_time(dto.getSchedule_time())
                .star(dto.getStar())
                .professor(dto.getProfessor())
                .status1(dto.getStatus1())
                .status2(dto.getStatus2())
                .build();
    }

}
