package klab.sugangstar.controller;

import klab.sugangstar.domain.Subject;
import klab.sugangstar.dto.SubjectCreateDto;
import klab.sugangstar.dto.SubjectProvideDto;
import klab.sugangstar.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/subjects")
    public void saveSubject(@RequestBody @Valid SubjectCreateDto request){
        subjectService.createSubject(request);
    }

    @GetMapping("/subjects")
    public List<List<String>> getSubjects(){
        List<List<String>> s = subjectService.findRandom();
        return s;
    }

    @DeleteMapping("/subjects/{id}")
    public void deleteSubject(@PathVariable Long id){
        subjectService.deleteById(id);
        //return id+"가 정상적으로 삭제 되었습니다.";
    }

}
