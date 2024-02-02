package uz.zako.zakoBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.CoursesPayload;
import uz.zako.zakoBackend.service.CoursesService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/api")
public class CoursesController {
    private final CoursesService coursesService;

    // Save Course
    @PostMapping("/admin/course/save")
    public ResponseEntity<?> addCourse(@RequestBody CoursesPayload coursesPayload){
        Result result=coursesService.saveCourse(coursesPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Edit course
    @PutMapping("/admin/course/edit/{id}")
    public ResponseEntity<?> editCourse(@RequestBody CoursesPayload coursesPayload,@PathVariable Long id){
        Result result=coursesService.editCourse(id,coursesPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Get all course
    @GetMapping("/auth/course/getAll")
    public ResponseEntity<?> getAll(){
        Result result=coursesService.getAll();
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Get course by id
    @GetMapping("/auth/course/find/{id}")
    public ResponseEntity<?> findCourse(@PathVariable Long id){
        Result result=coursesService.findById(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Delete course by id
    @DeleteMapping("/admin/course/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Result result=coursesService.deleteCourse(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }


}
