package uz.zako.zakoBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.zakoBackend.entity.Teacher;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.TeacherPayload;
import uz.zako.zakoBackend.service.serviceImpl.TeacherServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    // Save Teacher
    @PostMapping("/admin/teacher/save")
    public ResponseEntity<?> saveTeacher(@RequestBody TeacherPayload teacherPayload){
        Result result=teacherService.saveTeacher(teacherPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Edit Teacher
    @PutMapping("/admin/teacher/edit/{id}")
    public ResponseEntity<?> editTeacher(@RequestBody TeacherPayload teacherPayload,@PathVariable Long id){
        Result result=teacherService.editTeacher(id,teacherPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Get all Teacher
    @GetMapping("/auth/teacher")
    public ResponseEntity<?> getAll(){
        Result result=teacherService.getAll();
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Get Teacher by id
    @GetMapping("/admin/teacher/find/{id}")
    public ResponseEntity<?> findTeacher(@PathVariable Long id){
        Result result=teacherService.findById(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
    // Delete Teacher by id
    @DeleteMapping("/admin/teacher/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        Result result=teacherService.deleteTeacher(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    // Get Courses by id
    @GetMapping("/auth/teacher/find/{id}")
    public List<Teacher> getAllByCourses(@PathVariable Long courseId){
        return ResponseEntity.ok(teacherService.getAllByCourses(courseId)).getBody();
    }
}
