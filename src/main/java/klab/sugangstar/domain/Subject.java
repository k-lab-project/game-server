package klab.sugangstar.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="subject")
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    private String class_name;
    private String credit;
    private String korea_name;
    private String english_name;
    private String popularity;
    private String schedule_day;
    private String schedule_time;
    private String star;
    private String professor;


    // 생성 메서드
    public static Subject setSubject(String class_name,String credit,String korea_name, String english_name,
                                        String popularity, String schedule_day ,String schedule_time,String star,String professor){
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
        return subject;
    }

}
