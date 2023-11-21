package klab.sugangstar.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@Table(name="subject")
@Getter
@Setter
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
    private String star;
    private String professor;
    // 해당 과목이 요구하는 능력치
    private String status1;
    private String status2;


    // 생성 메서드
    public static Subject setSubject(String class_name, String credit, String korea_name, String english_name,
                                     String popularity, String schedule_day , String schedule_time, String star, String professor
                                        , String status1, String status2){
        Subject subject = new Subject();
        subject.setClass_name(class_name);
        subject.setCredit(credit);
        subject.setKorea_name(korea_name);
        subject.setEnglish_name(english_name);
        subject.setPopularity(popularity);
        subject.setSchedule_day(schedule_day);
        subject.setSchedule_time(schedule_time);
        subject.setStar(star);
        subject.setProfessor(professor);
        subject.setStatus1(status1);
        subject.setStatus2(status2);
        return subject;
    }

}
