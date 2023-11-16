package klab.sugangstar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SubjectCreateDto {
    private String class_name;
    private String credit;
    private String korea_name;
    private String english_name;
    private String popularity;
    private String schedule_day;
    private String schedule_time;
    private String star;
    private String professor;
    private String status1;
    private String status2;

}
