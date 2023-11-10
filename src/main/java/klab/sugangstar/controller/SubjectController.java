package klab.sugangstar.controller;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import klab.sugangstar.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/subject")
    public List<String> saveSubject(@RequestBody @Valid SubjectCreateDto request){
        subjectService.createSubject(request);
        List<String> s = new ArrayList<>();
        s.add(request.getClass_name());
        s.add(request.getEnglish_name());
        return s;
    }

    @GetMapping("/")
    public List<List<String>> provideSubject(){
        List<List<String>> s = subjectService.findRandom();
        return s;
    }
}
