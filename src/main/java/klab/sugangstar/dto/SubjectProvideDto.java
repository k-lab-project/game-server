package klab.sugangstar.dto;

import lombok.Data;

@Data
public class SubjectProvideDto {
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

    public SubjectProvideDto(Long id,String class_name, String credit, String korea_name, String english_name, String popularity, String schedule_day, String schedule_time, String star, String professor) {
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
    }
}